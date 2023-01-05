package my.board.web.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.entity.Post;
import my.board.domain.post.PostRepository;
import my.board.dto.PostSaveDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    @GetMapping
    public String postList(Model model) {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @GetMapping("{postId}")
    public String post(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("잘못된 요청입니다.")
        );
        model.addAttribute("post",post);
        return "posts/post";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        log.info("Get addForm");
        model.addAttribute("post", new Post());
        return "posts/addForm";
    }

    @PostMapping("/add")
    public String addPost(@Validated @ModelAttribute("post") PostSaveDto postSaveDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "posts/addForm";
        }

        //성공 로직
        Post savedPost = postRepository.save(new Post(postSaveDto));
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/posts/{postId}";
    }
}
