package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.dto.chat.ChatRoom;
import sangmyungdae.deliciousclimbing.dto.mate.*;
import sangmyungdae.deliciousclimbing.dto.user.User;
import sangmyungdae.deliciousclimbing.service.MateService;
import sangmyungdae.deliciousclimbing.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/mate")
@RequiredArgsConstructor
public class MateController {
    private final MateService service;
    private final UserService userService;

    @GetMapping
    public String mateListPage(@RequestParam(required = false) Long mountainId,
                               @RequestParam(required = false) Boolean rscheck,
                               @PageableDefault(size = 8, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                               Model model) {

        if(rscheck == null)
            rscheck = false;

        MateSearchDto dto = MateSearchDto.builder()
                .mountainId(mountainId)
                .recruitStatusFiltering(rscheck)
                .build();
        Page<MatePost> matePostList = service.getMateList(dto, pageable);
        model.addAttribute("matePostList", matePostList);

        return "mateMain";
    }

    @GetMapping(value = "/{mateId}")
    public String mateDetailPage(@PathVariable Long mateId, @ModelAttribute("mateComment") MateCommentDto mateCommentDto, Model model) {

        User user = userService.getUser();
        model.addAttribute("currentUser", user);


        MatePost matePost = service.getPostDetail(mateId);
        model.addAttribute("matePost", matePost);

        return "mateDetail";
    }

    @GetMapping(value = "/edit/{mateId}")
    public String mateEditPage(@PathVariable Long mateId, Model model) {
        Mate mate = service.getPostDetail(mateId).getMate();
        model.addAttribute("mateInfo", mate);

        return "mateUpdate";
    }

    @GetMapping(value = "/write")
    public String mateWritePage(@ModelAttribute("mateInfo") MateDto mateDto) {
        return "mateCreate";
    }


    @PostMapping(value = "/write")
    public String createPost(@ModelAttribute("mateInfo") MateDto dto, RedirectAttributes redirectAttributes) {
        log.info("mateInfo={}", dto);

        User user = userService.getUser();

        //임시 코드
        MateDto mateDto = MateDto.builder().recruitCount(dto.getRecruitCount())
                .mountainId(dto.getMountainId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .recruitStatus(dto.getRecruitStatus())
                .recruitDate(dto.getRecruitDate())
                .recruitCount(dto.getRecruitCount())
                .build();

        Mate mate = service.createPost(user.getId(), mateDto);
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

        User user = userService.getUser();
        service.createComment(mateId, user.getId(), dto);

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

    @RequestMapping(value = "/{sido}/{sigungu}", produces = "application/json;charset=UTF-8", method= RequestMethod.GET)
    @ResponseBody
    public void get_option2(HttpServletResponse res, @PathVariable String sido, @PathVariable String sigungu) throws IOException {

//        List<Option> options = OptionService.findOption2(option1);
//        List<String> optionList = new ArrayList();
//
//        for (int i = 0; i < options.size(); i++) {
//            optionList.add(options.get(i).getOption2());
//        }
        res.setContentType("application/json;charset=UTF-8");
        log.info("sido={}, sigungu={}", sido, sigungu);
        List<MateFamousMountain> mountainList = service.getMountainList(sido, sigungu);
        log.info("mountainList={}", mountainList);


        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < mountainList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mtName", mountainList.get(i).getMtName());
            jsonObject.put("id", mountainList.get(i).getId());
            jsonArray.put(jsonObject);
        }

        log.info("result={}", jsonArray.toString());
        PrintWriter pw = res.getWriter();
        pw.print(jsonArray.toString());
        pw.flush();
        pw.close();
    }

    @PostMapping("/chat/{mateId}")
    public String createChatRoom(@PathVariable Long mateId) {
        ChatRoom chatRoom = service.createChatting(mateId);

        return "redirect:/chat/room/" + chatRoom.getType() + "/" + chatRoom.getId();
    }
}
