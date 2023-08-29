package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.dto.mate.*;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.service.MateService;
import sangmyungdae.deliciousclimbing.service.UserService;

//TODO: 그 조회할때나 글 작성할때 주소코드 날라오면 산 목록 반환해주는 부분 해야합니다.

@Slf4j
@Controller
@RequestMapping("/mate")
@RequiredArgsConstructor
public class MateController {
    private final MateService service;
    private final UserService userService;

    @GetMapping
    public String mateListPage(@RequestParam(required = false) Long mountainId,
                               @RequestParam(required = false) boolean recruitStatusFiltering,
                               @PageableDefault(size = 8, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                               Model model) {

        MateSearchDto dto = MateSearchDto.builder()
                .mountainId(mountainId)
                .recruitStatusFiltering(recruitStatusFiltering)
                .build();
        Page<MatePost> matePostList = service.getMateList(dto, pageable);
        model.addAttribute("matePostList", matePostList);

        return "mateMain";
    }

    @GetMapping(value = "/{mateId}")
    public String mateDetailPage(@PathVariable Long mateId, @ModelAttribute("mateComment") MateCommentDto mateCommentDto, Model model) {

        MatePost matePost = service.getPostDetail(mateId);
        model.addAttribute("matePost", matePost);

        log.info("recruitDate={}, updatedAt={}", matePost.getMate().getRecruitDate(), matePost.getMate().getUpdatedAt());

        return "mateDetail";
    }

    @GetMapping(value = "/edit/{mateId}")
    public String mateEditPage(@PathVariable Long mateId, Model model) {
        Mate mate = service.getPostDetail(mateId).getMate();
        model.addAttribute("mate", mate);

        return "mateUpdate";
    }

    @GetMapping(value = "/write")
    public String mateWritePage(@ModelAttribute("mateInfo") MateDto mateDto) {
        return "mateCreate";
    }


    @PostMapping(value = "/write")
    public String createPost(@ModelAttribute("mateInfo") MateDto dto, RedirectAttributes redirectAttributes) {
        //세션에서 사용자 정보를 갖고와야함, userId
        //Todo: 나머지 코드 작성
        log.info("mateInfo={}", dto);

        //임시 코드
        User user = userService.getUser(33L);
        MateDto mateDto = MateDto.builder().recruitCount(dto.getRecruitCount())
                .mountainId(1L)
                .title(dto.getTitle())
                .content(dto.getContent())
                .recruitStatus(Boolean.TRUE)
                .recruitDate(dto.getRecruitDate())
                .recruitCount(dto.getRecruitCount())
                .build();

        Mate mate = service.createPost(33L, mateDto);
        redirectAttributes.addAttribute("mateId", mate.getId());

        return "redirect:/mate/{mateId}";
    }

    @PostMapping(value = "/edit/{mateId}")
    public String updatePost(@ModelAttribute("mate") MateDto dto, @PathVariable Long mateId, RedirectAttributes redirectAttributes) {
        service.updatePost(mateId, dto);

        return "redirect:/mate/{mateId}";
    }

    @PostMapping(value = "/delete/{mateId}")
    public String deletePost(@PathVariable Long mateId) {
        service.deletePost(mateId);

        return "redirect:/mate";
    }

    @PostMapping(value = "/{mateId}/comment/write")
    public String createComment(@PathVariable Long mateId, @ModelAttribute("mateComment") MateCommentDto dto) {
        //세션에서 사용자 정보를 갖고와야함, userId
        //Todo: 나머지 코드 작성
//        service.createComment(mateId, userId, dto)

        // 임시 코드
        service.createComment(mateId, 33L, dto);

        log.info("content={}", dto.getContent());

        return "redirect:/mate/{mateId}";
    }

    @PostMapping(value = "/{mateId}/comment/edit/{commentId}")
    public String updateComment(@ModelAttribute MateCommentDto dto,
                                  @PathVariable Long mateId,
                                  @PathVariable Long commentId) {
        service.updateComment(commentId, dto);

        return "redirect:/mate/{mateId}";
    }

    @PostMapping(value = "/{mateId}/comment/delete/{commentId}")
    public String deleteComment(@PathVariable Long mateId,
                           @PathVariable Long commentId) {

        service.deleteComment(mateId, commentId);

        return "redirect:/mate/{mateId}";
    }

    @PostMapping(value = "/{mateId}/file/write")
    public String createFile(@ModelAttribute("file") MateFileDto dto, @PathVariable Long mateId) {
        service.createFile(mateId, dto);

        return "redirect:/mate/{mateId}";
    }

    @PostMapping(value = "/{mateId}/file/update/{fileId}")
    public String updateFile(@ModelAttribute("file") MateFileDto dto,
                               @PathVariable Long mateId,
                               @PathVariable Long fileId) {
        service.updateFile(fileId, dto);

        return "redirect:/mate/{mateId}";
    }

    @PostMapping(value = "/{mateId}/file/delete/{fileId}")
    public String deleteFile(@PathVariable Long mateId,
                           @PathVariable Long fileId) {
        service.deleteFile(mateId, fileId);

        return "redirect:/mate/{mateId}";
    }
}
