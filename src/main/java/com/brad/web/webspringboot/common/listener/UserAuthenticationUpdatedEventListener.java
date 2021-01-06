package com.brad.web.webspringboot.common.listener;

import com.brad.web.webspringboot.common.annotation.Listener;
import com.brad.web.webspringboot.common.authentication.ShiroRealm;
import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.event.UserAuthenticationUpdatedEvent;
import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;

import java.util.Set;

/**
 * {@link UserAuthenticationUpdatedEvent}事件监听器
 *
 * @author MrBird
 */
@Slf4j
@Listener
@RequiredArgsConstructor
public class UserAuthenticationUpdatedEventListener {

    private final ShiroRealm realm;

    @EventListener
    @Async(FebsConstant.ASYNC_POOL)
    public void onUserAuthenticationUpdated(@NonNull UserAuthenticationUpdatedEvent event) {
        Set<Long> userIds = event.getUserIds();
        if (CollectionUtils.isNotEmpty(userIds)) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            userIds.forEach(realm::clearCache);
            event.cleanSet(userIds);
            log.info("clean user [userId: {}] authentication cache,which took {}", userIds, stopwatch.stop());
        }
    }
}
