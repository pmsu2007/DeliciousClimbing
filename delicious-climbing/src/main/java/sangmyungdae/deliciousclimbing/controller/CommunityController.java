package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;
import sangmyungdae.deliciousclimbing.dto.community.Post;
import sangmyungdae.deliciousclimbing.dto.community.PostDto;
import sangmyungdae.deliciousclimbing.dto.community.PostSearchDto;
import sangmyungdae.deliciousclimbing.dto.community.CommentDto;
import sangmyungdae.deliciousclimbing.dto.community.FileDto;
import sangmyungdae.deliciousclimbing.service.CommunityService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping("/{type}")
    public String communityListPage(@PathVariable BoardType type,
                                    @RequestParam(required = false) String title,
                                    @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                    Model model) {
        PostSearchDto dto = PostSearchDto.builder()
                .title(title)
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
                                      FileDto fileDto,
                                      Model model) {
        model.addAttribute("postDto", postDto);
        model.addAttribute("fileDto", fileDto);
        return "communityCreate";
    }

    @GetMapping("/update/{postId}")
    public String communityUpdatePage(@PathVariable Long postId, Model model) {
        Post post = communityService.getPostDetail(postId);
        model.addAttribute(post);

        return "communityUpdate";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDto dto,
                             RedirectAttributes redirectAttributes) {
        // user_id DTO에 추가
        communityService.createPost(dto);
        return "redirect:/posts/{type}/{postId}";
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@ModelAttribute PostDto dto,
                             @PathVariable Long postId,
                             RedirectAttributes redirectAttributes) {
        // 사용자 auth 확인
        // user_id DTO에 추가
        communityService.updatePost(postId, dto);
        return "redirect:/posts/{type}/{postId}";
    }

    @PostMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId,
                             RedirectAttributes redirectAttributes) {
        communityService.deletePost(postId);
        return "redirect:/posts/{type}";
    }

    @PostMapping("/create/{postId}/comment")
    public String createComment(@PathVariable Long postId,
                                @ModelAttribute CommentDto dto,
                                RedirectAttributes redirectAttributes) {
        // user_id DTO에 추가
        dto.setPostId(postId);
        communityService.createComment(dto);
        return "redirect:/posts/{type}/{postId}";
    }

    @PostMapping("/update/{postId}/comment/{commentId}")
    public String updateComment(@PathVariable Long postId,
                                @PathVariable Long commentId,
                                @ModelAttribute CommentDto dto,
                                RedirectAttributes redirectAttributes) {
        // 사용자 auth 확인
        // user_id DTO에 추가
        dto.setPostId(postId);
        communityService.updateComment(commentId, dto);
        return "redirect:/posts/{type}/{postId}";
    }

    @PostMapping("/delete/{postId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId,
                                RedirectAttributes redirectAttributes) {
        communityService.deleteComment(commentId);
        return "redirect:/posts/{type}/{postId}";
    }

    @PostMapping("/create/{postId}/file")
    public String createFile(@PathVariable Long postId,
                             @ModelAttribute FileDto dto,
                             RedirectAttributes redirectAttributes) {

        dto.setPostId(postId);
        communityService.createFile(dto);
        return "redirect:/posts/{type}/{postId}";
    }

    @PostMapping("/update/{postId}/file/{fileId}")
    public String updateFile(@PathVariable Long postId,
                             @PathVariable Long fileId,
                             @ModelAttribute FileDto dto,
                             RedirectAttributes redirectAttributes) {

        dto.setPostId(postId);
        communityService.updateFile(fileId, dto);
        return "redirect:/posts/{type}/{postId}";
    }

    @PostMapping("/delete/{postId}/file/{fileId}")
    public String deleteFile(@PathVariable Long fileId,
                             RedirectAttributes redirectAttributes) {
        communityService.deleteFile(fileId);
        return "redirect:/posts/{type}/{postId}";
    }
}