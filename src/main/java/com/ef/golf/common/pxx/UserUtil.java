package com.ef.golf.common.pxx;

import com.ef.golf.common.pxx.model.User;
import com.pingplusplus.exception.*;

import java.util.HashMap;
import java.util.Map;

public class UserUtil {

    private String appId;

    public UserUtil(String appId) {
        this.appId = appId;
    }

    public User createUser(){
        User user = null;
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", "");
        userMap.put("object", "user");
        userMap.put("type", "customer");
        userMap.put("available_balance", 0);
        userMap.put("withdrawable_balance", 0);
        userMap.put("created", System.currentTimeMillis()/1000);
        userMap.put("disabled", false);
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", appId);
        userMap.put("app", app);
        try {
            user = User.create(userMap);
            String userString = user.toString();
            System.out.println(userString);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }

        return user;

    }


}
