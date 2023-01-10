package my.board.web.comment;

import lombok.RequiredArgsConstructor;
import my.board.domain.comment.CommentService;
import my.board.dto.CommentSaveDto;
import my.board.dto.PostSaveDto;
import my.board.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/{postId}/comments")
    public String commentList(@PathVariable Long postId) {
        commentService.commentList(postId);
        return "Get CommentList";
    }

    @ResponseBody
    @PostMapping("/{postId}/comments")
    public String comment(@PathVariable Long postId, @RequestBody CommentSaveDto commentSaveDto,
                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.commentSave(postId,commentSaveDto,userDetails.getUser());
        return "commentSaved";
    }
}
