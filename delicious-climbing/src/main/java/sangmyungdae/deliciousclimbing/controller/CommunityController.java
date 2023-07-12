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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.config.FileStore;
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
        model.addAttribute(post);

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

    @PostMapping("/likes/{postId}/{userId}")
    public String likePost(@PathVariable Long postId,
                           @PathVariable Long userId) {
        Post post = communityService.postLike(userId, postId);

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

//    @PostMapping("/update/{postId}/comment/{commentId}")
//    public String updateComment(@PathVariable Long postId,
//                                @PathVariable Long commentId,
//                                @ModelAttribute CommentDto dto,
//                                RedirectAttributes redirectAttributes) {
//        // 사용자 auth 확인
//        // user_id DTO에 추가
//        dto.setPostId(postId);
//        communityService.updateComment(commentId, dto);
//        return "redirect:/posts/{type}/{postId}";
//    }

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

//    @PostMapping("/create/{postId}/file")
//    public String createFile(@PathVariable Long postId,
//                             @ModelAttribute FileDto dto,
//                             RedirectAttributes redirectAttributes) {
//
//        dto.setPostId(postId);
//        communityService.createFile(dto);
//        return "redirect:/posts/{type}/{postId}";
//    }

//    @PostMapping("/update/{postId}/file/{fileId}")
//    public String updateFile(@PathVariable Long postId,
//                             @PathVariable Long fileId,
//                             @ModelAttribute FileDto dto,
//                             RedirectAttributes redirectAttributes) {
//
//        dto.setPostId(postId);
//        communityService.updateFile(fileId, dto);
//        return "redirect:/posts/{type}/{postId}";
//    }

//    @PostMapping("/delete/{postId}/file/{fileId}")
//    public String deleteFile(@PathVariable Long fileId,
//                             RedirectAttributes redirectAttributes) {
//        communityService.deleteFile(fileId);
//        return "redirect:/posts/{type}/{postId}";
//    }
}