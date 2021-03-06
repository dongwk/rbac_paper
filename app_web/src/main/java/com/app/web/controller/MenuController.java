/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.core.common.ThrowBiz;
import com.app.model.model.Menu;
import com.app.service.service.MenuService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.mo.LoginUserMo;
import com.app.web.mo.MenuMo;
import com.app.web.mo.base.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseRestController<MenuService, Menu> {

    @Autowired
    private MenuService menuService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody(required = false) PageMo pageMo){
        Menu menu = new Menu();
        IPage<Menu> page = menuService.listPage(getPage(), menu, Boolean.TRUE, "seq");
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(menuService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody MenuMo menuMo){

        ThrowAssert.isNull(menuMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isBlank(menuMo.getName(), "menu.add.name-empty");
        ThrowAssert.isTrue(menuMo.getName().trim().length() > Constants.INT_16, "menu.add.name-max");
        ThrowAssert.isNull(menuMo.getParentId(), "menu.upd.parentid-empty");
        ThrowAssert.isTrue(menuMo.getParentId() < Constants.INT_1 || menuMo.getParentId() > Constants.INT_1, "menu.add.name-max");
        menuMo.setName(menuMo.getName().trim());

        Menu menu = MoToDoUtils.toAddDO(menuMo, Menu.class);
        menuService.save(menu);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody MenuMo menuMo){

        ThrowAssert.isNull(menuMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isTrue(menuMo.getId() == null || menuMo.getId() < Constants.INT_1, "menu.upd.id-empty");
        ThrowAssert.isBlank(menuMo.getName(), "menu.add.name-empty");
        ThrowAssert.isTrue(menuMo.getName().trim().length() > Constants.INT_16, "menu.add.name-max");
        menuMo.setName(menuMo.getName().trim());

        Menu menu = MoToDoUtils.toUpdDO(menuMo, Menu.class);
        menuService.updateById(menu);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(menuService.removeById(id));
    }

    @Authorization
    @GetMapping("/list")
    public R<?> list(@LoginUser LoginUserMo loginUserMo){
        return R.SUCCESS(menuService.listByUserId(loginUserMo.getId()));
    }


    @Authorization
    @GetMapping("/tree")
    public R<?> tree(){
        Menu menu = new Menu();
        List<Menu> list = menuService.list(menu, Boolean.TRUE, "seq");
        return R.SUCCESS(buildMenu(list));
    }

    /**
     * list menu 转换成树结构
     * @param menus
     * @return
     */
    private List<Menu> buildMenu(List<Menu> menus) {
        List<Menu> ret = Lists.newArrayList();
        Map<Integer, Menu> map = menus.stream().collect(Collectors.toMap(Menu::getId, Function.identity()));
        menus.forEach(e -> {
            if (e.getParentId() != null) {
                if (e.getParentId().intValue() == Constants.INT_0) {
                    ret.add(e);
                } else {
                    Menu menu = map.get(e.getParentId());
                    if (menu != null) {
                        if (menu.getChildren() == null) {
                            menu.setChildren(Lists.newArrayList());
                        }
                        menu.getChildren().add(e);
                    }
                }
            }
        });
        return ret;
    }
}