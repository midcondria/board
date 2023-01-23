package my.board.web.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.comment.CommentService;
import my.board.dto.CommentResponseDto;
import my.board.dto.CommentSaveDto;
import my.board.dto.PostSaveDto;
import my.board.dto.PostUpdateDto;
import my.board.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/{postId}/comments")
    public ResponseEntity commentList(@PathVariable Long postId) {
        List<CommentResponseDto> commentList = commentService.findComments(postId);
        DTO dto = new DTO(commentList);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class DTO<T> {
        private T data;
    }

    @ResponseBody
    @PostMapping("/{postId}/comments")
    public String comment(@PathVariable Long postId, @RequestBody CommentSaveDto commentSaveDto,
                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.commentSave(postId,commentSaveDto,userDetails.getUser());
        return "commentSaved";
    }

//    @PostMapping({"/{postId}/comments/{commentId}/edit"})
//    public String edit(@PathVariable Long postId,@PathVariable Long commentId, @Validated @ModelAttribute("post") PostUpdateDto postUpdateDto, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            log.info("errors={}", bindingResult);
//            return "posts/editForm";
//        }
//        commentService.updatePost(postId, postUpdateDto);
//        return "redirect:/posts/{postId}";
//    }

    @ResponseBody
    @DeleteMapping({"/comments/{commentId}"})
    public String delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return "comment Removed";
    }
}
