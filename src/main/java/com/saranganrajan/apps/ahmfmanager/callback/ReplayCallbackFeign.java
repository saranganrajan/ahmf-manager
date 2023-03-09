package com.saranganrajan.apps.ahmfmanager.callback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "replayCallback", url = "${replay.callback.url}")
public interface ReplayCallbackFeign {
    @PostMapping(path = "${replay.callback.path}", produces = "application/json")
    <T> void replayCallback(@RequestBody List<T> replayEntities);
}
