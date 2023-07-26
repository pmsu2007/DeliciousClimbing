package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sangmyungdae.deliciousclimbing.util.FileStore;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;
import sangmyungdae.deliciousclimbing.dto.community.Post;
import sangmyungdae.deliciousclimbing.dto.community.PostDto;
import sangmyungdae.deliciousclimbing.dto.community.PostSearchDto;
import sangmyungdae.deliciousclimbing.dto.community.CommentDto;
import sangmyungdae.deliciousclimbing.service.CommunityService;
import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class CommunityController {
    private final CommunityService communityService;
    private final FileStore fileStore;

    @GetMapping("/{type}")
    public String communityListPage(@PathVariable BoardType type,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String sort,
                                    @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                    Model model) {

        PostSearchDto dto = PostSearchDto.builder()
                .title(title)
                .sort(sort)
                .type(type)
                .build();

        Page<Post> posts = communityService.getPostList(dto, pageable);
        model.addAttribute("type", type);
        model.addAttribute("posts", posts);
        return "communityList";
    }

    @GetMapping("/my")
    public String communityMyPostPage(@PageableDefault(size = 10) Pageable pageable,
                                      Model model) {
        // Page<Post> posts = communityService.getMyPostList(userId, pageable);
        // model.addAttribute(posts);
        return "commList";
    }

    @GetMapping("/{type}/{postId}")
    public String communityDetailPage(@PathVariable Long postId,
                                      CommentDto commentDto,
                                      Model model) {
        Post post = communityService.getPostDetail(postId);
        model.addAttribute("commentDto", commentDto);
        model.addAttribute("post", post);
        return "communityDetail";
    }

    @GetMapping("/create")
    public String communityCreatePage(PostDto postDto,
                                      Model model) {
        model.addAttribute("postDto", postDto);
        model.addAttribute("types", BoardType.values());
        return "communityCreate";
    }

    @GetMapping("/update/{postId}")
    public String communityUpdatePage(@PathVariable Long postId,
                                      PostDto postDto,
                                      Model model) {
        Post post = communityService.getPostDetail(postId);
        model.addAttribute("postDto", postDto);
        model.addAttribute("post", post);

        return "communityUpdate";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDto postDto,
                             MultipartFile[] files ) {
        for (MultipartFile file :
                files) {
            System.out.println("file = " + file.toString());
        }

        // user_id DTO에 추가
        Post post = communityService.createPost(postDto, files);

        return "redirect:/posts/" + post.getType() + "/" + post.getId();
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@ModelAttribute PostDto dto,
                             @PathVariable Long postId) {
        // 사용자 auth 확인
        // user_id DTO에 추가
        Post post = communityService.updatePost(postId, dto);
        return "redirect:/posts/" + post.getType() + "/" + post.getId();
    }

    @PostMapping("/likes/{postId}")
    public String likePost(@PathVariable Long postId) {
        Post post = communityService.postLike(postId);

        return "redirect:/posts/" + post.getType() + "/" + post.getId();
    }

    @PostMapping("/delete/{type}/{postId}")
    public String deletePost(@PathVariable BoardType type,
                             @PathVariable Long postId) {
        communityService.deletePost(postId);
        return "redirect:/posts/" + type;
    }

    @PostMapping("/create/{type}/{postId}/comment")
    public String createComment(@PathVariable BoardType type,
                                @PathVariable Long postId,
                                @ModelAttribute CommentDto dto) {
        // user_id DTO에 추가
        communityService.createComment(postId, dto);
        return "redirect:/posts/" + type + "/" + postId;
    }


    @PostMapping("/delete/{type}/{postId}/comment/{commentId}")
    public String deleteComment(@PathVariable BoardType type,
                                @PathVariable Long postId,
                                @PathVariable Long commentId) {
        communityService.deleteComment(commentId);
        return "redirect:/posts/" + type + "/" + postId;
    }

    @ResponseBody
    @GetMapping("/files/{filename}")
    public Resource getFile(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }


}