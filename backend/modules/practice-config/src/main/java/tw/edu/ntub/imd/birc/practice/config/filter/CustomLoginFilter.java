package tw.edu.ntub.imd.birc.practice.config.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tw.edu.ntub.imd.birc.practice.config.handler.CustomAuthenticationFailHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    public CustomLoginFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler) {
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(new CustomAuthenticationFailHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException("認證方法不支援：" + request.getMethod());
        }
        UsernamePasswordAuthenticationToken authentication = resolveAuthenticationFromRequestBody(request);
        setDetails(request, authentication);
        return getAuthenticationManager().authenticate(authentication);
    }

    private UsernamePasswordAuthenticationToken resolveAuthenticationFromRequestBody(HttpServletRequest request)
            throws AuthenticationException {
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            if (sb.length() == 0) {
                throw new AuthenticationServiceException("請求內容為空，請以 JSON 傳入 account 與 password");
            }
            JsonNode json = new ObjectMapper().readTree(sb.toString());
            JsonNode accountNode = json.get("account");
            JsonNode passwordNode = json.get("password");
            if (accountNode == null || passwordNode == null) {
                throw new AuthenticationServiceException("請提供 account 與 password");
            }
            return new UsernamePasswordAuthenticationToken(accountNode.asText(), passwordNode.asText());
        } catch (IOException e) {
            throw new AuthenticationServiceException("登入請求解析失敗", e);
        }
    }
}