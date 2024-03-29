package my.board.web.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.comment.CommentService;
import my.board.domain.entity.Post;
import my.board.domain.entity.User;
import my.board.domain.post.PostRepository;
import my.board.domain.post.PostService;
import my.board.dto.MenuRequestDto;
import my.board.dto.PostResponseDto;
import my.board.dto.PostSaveDto;
import my.board.dto.PostUpdateDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentService commentService;

//    @ResponseBody
//    @GetMapping
//    public Result postList() {
//        List<PostResponseDto> postList = postService.findPosts();
//
//        return new Result(postList);
//    }
//
//    @Data
//    @AllArgsConstructor
//    static class Result<T> {
//        private T postList;
//    }


    @PostMapping(value = "/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getJson(@RequestBody MenuRequestDto menuList) {
        menuList.getMenuList().forEach(
                m -> {
                    log.info("id = " + m.getId());
                    log.info("Name = " + m.getMenuName());
                    log.info("amount = "+ m.getAmount());
                }
        );
        log.info("lastName = "+ menuList.getName());
        log.info("size = " + menuList.getMenuList().size());

        User user = new User();
        menuList.getMenuList().forEach(
                m -> {
                    Post savedPost = postRepository.save(new Post(new PostSaveDto(m.getMenuName(),m.getId(),m.getAmount()), user));
                }
        );
        return "done";
    }

    @GetMapping
    public String postList(Model model) {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @ResponseBody
    @GetMapping("/{postId}")
    public PostPage post(@PathVariable Long postId) {
        Post post = postService.findPost(postId);

        return new PostPage(new PostResponseDto(post));
    }

    @Data
    @AllArgsConstructor
    static class PostPage<T1> {
        private T1 post;

    }

    @GetMapping("/add")
    public String addForm(Model model) {
        log.info("Get addForm");
        model.addAttribute("post", new Post());
        return "posts/addForm";
    }

    @PostMapping("/add")
    public String addPost(@Validated @ModelAttribute("post") PostSaveDto postSaveDto,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "posts/addForm";
        }

        //성공 로직
        User user = new User();
        Post savedPost = postRepository.save(new Post(postSaveDto, user));
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 요청입니다.")
        );
        model.addAttribute("post", post);
        return "posts/editForm";
    }

    @PatchMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId,
                       @Validated @ModelAttribute("post") PostUpdateDto postUpdateDto,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "posts/editForm";
        }
        postService.updatePost(postId, postUpdateDto);
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{postId}")
    public String delete(@PathVariable Long postId) {
        postService.delete(postId);
        return "posts/posts";
    }
}
