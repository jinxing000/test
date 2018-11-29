package com.ef.golf.core.structure.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 消息切面
 * 
 * @author ye.jinhui
 * @date 2016年7月15日
 */
@Aspect
@Component
public class MessageAspect {

//	@Resource(name = "messageBiz")
//	private MessageBiz messageBiz;

	@Before(value = "execution(* com.ef.golf..action.*.*(..))")
	public void handleMessage() {
//		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//		BimWebUser bimWebUser = (BimWebUser) session.getAttribute("webUser");
		// 只有登录了采取判断redis中的hash是否有message
//		if (bimWebUser != null) {
//			messageBiz.redisMessageCountHashSaveSession(session, bimWebUser.getWebUserId());
//		}
	}

}
