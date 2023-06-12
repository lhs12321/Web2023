package com.example.web.springmvc;

import com.example.web.HttpUtils;
import com.example.web.dao.Limit;
import com.example.web.dao.User;
import com.example.web.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

/**
 * Servlet API를 사용하는 Controller
 */
@AllArgsConstructor
@Slf4j
public class UserControllerV1 {

    private final UserDao userDao;

    /**
     * just forward
     */
    @GetMapping({"/user/signinForm", "/user/signupForm", "/user/myInfo",
            "/user/passwordEdit"})
    public void mapDefault(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpUtils.forward(req, resp);
    }

    /**
     * 회원목록
     */
    @GetMapping("/user/userList")
    public void userList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Limit limit =
                new Limit(req.getParameter("count"), req.getParameter("page"));
        req.setAttribute("limit", limit);
        List<User> userList = userDao.listUsers(limit);
        req.setAttribute("userList", userList);
        HttpUtils.forward(req, resp);
    }

    /**
     * 회원정보
     */
    @GetMapping("/user/userInfo")
    public void userInfo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("user",
                userDao.getUser(Integer.parseInt(req.getParameter("userId"))));
        HttpUtils.forward(req, resp);
    }

    /**
     * 회원가입
     */
    @PostMapping("/user/signup")
    public void signup(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User user = new User();
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setName(req.getParameter("name"));

        try {
            userDao.addUser(user);
            // 등록 성공
            signin(req, resp);
        } catch (DataAccessException e) { // 등록 실패
            log.error(e.getCause().toString());
            HttpUtils.redirect(req, resp, "/app/user/signupForm?mode=FAILURE");
        }
    }

    /**
     * 로그인
     */
    @PostMapping("/user/signin")
    public void signin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String redirectUrl =
                StringUtils.defaultIfEmpty(req.getParameter("redirectUrl"),
                        req.getContextPath() + "/app/user/userList");
        try {
            User user = userDao.login(email, password);
            // 로그인 성공
            HttpSession session = req.getSession();
            session.setAttribute("me_userId", user.getUserId());
            session.setAttribute("me_name", user.getName());
            session.setAttribute("me_email", user.getEmail());
            resp.sendRedirect(redirectUrl);
        } catch (DataAccessException e) {
            // 로그인 실패
            HttpUtils.redirect(req, resp,
                    "/app/user/signinForm?mode=FAILURE&redirectUrl=" +
                            HttpUtils.encodeUrl(redirectUrl));
        }
    }

    /**
     * 비밀번호변경
     */
    @PostMapping("/user/updatePassword")
    public void updatePassword(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int userId = (int) req.getSession().getAttribute("me_userId");
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");

        int updatedRows =
                userDao.updatePassword(userId, currentPassword, newPassword);
        if (updatedRows >= 1) // 업데이트 성공
            HttpUtils.redirect(req, resp, "/app/user/myInfo");
        else  // 업데이트 실패
            HttpUtils.redirect(req, resp, "/app/user/passwordEdit?mode=FAILURE");
    }

    /**
     * 로그아웃
     */
    @GetMapping("/user/signout")
    public void signout(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.getSession().invalidate();
        HttpUtils.redirect(req, resp, "/app/user/userList");
    }
}