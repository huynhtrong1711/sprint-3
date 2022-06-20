package com.example.bookstoremanagement.security.jwt;
import com.example.bookstoremanagement.security.userprinciple.AccountDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Autowired
    JwtProvider jwtProvider;
    //goi userDetailSevice cua he thong de goi phuong thuc lay userDetail tu username
    @Autowired
    AccountDetailService accountDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getJwt(request);
            if(token!= null && jwtProvider.validateToken(token)){
                String userName = jwtProvider.getUserNameFromToken(token);
                //gan userDetail cua he thong thanh userdetail cua minh trong model
                UserDetails userDetails = accountDetailService.loadUserByUsername(userName);

                //set user len website cua minh
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }catch (Exception e){
            logger.error("Can not set user authentication -> massage", e);
        }
        filterChain.doFilter(request,response);
    }
    //Phuong thucs lay token tu request
    private String getJwt(HttpServletRequest request){
        //lay content cua header co ten la Authorization
        String authHeader = request.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            return authHeader.replace("Bearer","");
        }
        return null;
    }
}
