package com.app.manage.web.controller.base;

import com.app.common.web.common.R;
import org.springframework.web.bind.annotation.*;

public class RestSupportController extends BaseController {

    @GetMapping("/{id}")
    public R<?> get(@PathVariable Object id) {
        return null;
    }

    @GetMapping
    public R<?> list(Object param) {
        return null;
    }

    @PostMapping
    public R<?> add(Object obj) {
        return null;
    }

    @DeleteMapping
    public R<?> delete(Object id) {
        return null;
    }

    @PutMapping
    public R<?> update(Object obj) {
        return null;
    }
}
