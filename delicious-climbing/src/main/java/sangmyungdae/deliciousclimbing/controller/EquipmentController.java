package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.domain.converter.EquipmentTypeConverter;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.dto.*;
import sangmyungdae.deliciousclimbing.service.EquipmentService;

import java.io.File;

@RequiredArgsConstructor
@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    private final EquipmentTypeConverter equipmentTypeConverter;
    @GetMapping("/{type}")
    public String equipmentListPage(@PathVariable String type,
                                    @RequestParam(required = false) Long code,
                                    @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                    Model model) {
        EquipmentSearchDto dto = EquipmentSearchDto.builder()
                .equipmentType(equipmentTypeConverter.convert(type))
                .adressCode(code)
                .build();

        Page<Equipment> equipments = equipmentService.getPostList(dto, pageable);
        model.addAttribute("equipments", equipments);
        return "equipMain";
    }


    @GetMapping("/{type}/{postId}")
    public String equipmentDetailPage(@PathVariable Long postId,
                                      Model model) {
        Equipment equipment = equipmentService.getPostDetail(postId);
        model.addAttribute(equipment);
        return "equipDetail";
    }

    @GetMapping("/create")
    public String equipmentCreatePage() {
        return "equipCreate";
    }

    @GetMapping("/update/{postId}")
    public String equipmentUpdatePage(@PathVariable Long postId, Model model) {
        Equipment equipment =equipmentService.getPostDetail(postId);
        model.addAttribute(equipment);

        return "equipUpdate";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute EquipmentDto dto,
                             RedirectAttributes redirectAttributes) {
        // user_id DTO에 추가
        equipmentService.createPost(dto);
        return "redirect:/equipments/{type}/{postId}";
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@ModelAttribute EquipmentDto dto,
                             @PathVariable Long postId,
                             RedirectAttributes redirectAttributes) {
        // 사용자 auth 확인
        // user_id DTO에 추가
        equipmentService.updatePost(postId, dto);
        return "redirect:/equipments/{type}/{postId}";
    }

    @PostMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId,
                             RedirectAttributes redirectAttributes) {
        return "redirect:/equipments/{type}";
    }


    @PostMapping("/create/{postId}/file")
    public String createFile(@PathVariable Long postId,
                             @ModelAttribute EquipmentFileDto dto,
                             RedirectAttributes redirectAttributes) {

        dto.setPostId(postId);
        equipmentService.createFile(dto);
        return "redirect:/equipments/{type}/{postId}";
    }

    @PostMapping("/update/{postId}/file/{fileId}")
    public String updateFile(@PathVariable Long postId,
                             @PathVariable Long fileId,
                             @ModelAttribute EquipmentFileDto dto,
                             RedirectAttributes redirectAttributes) {

        dto.setPostId(postId);
        equipmentService.updateFile(fileId, dto);
        return "redirect:/equipments/{type}/{postId}";
    }

    @PostMapping("/delete/{postId}/file/{fileId}")
    public String deleteFile(@PathVariable Long fileId,
                             RedirectAttributes redirectAttributes) {
        equipmentService.deleteFile(fileId);
        return "redirect:/equipments/{type}/{postId}";
    }
}


