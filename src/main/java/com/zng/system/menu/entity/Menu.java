package com.zng.system.menu.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "menu")
public class Menu extends CommonEntity {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_menu")
    @GenericGenerator(
            name = "sequence_generator_menu",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "menu"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "icon")
    private String icon;

    @Column(name = "is_hide",nullable = false)
    private Boolean isHide;

    @ManyToOne
    @JoinColumn(name = "p_id")
    private Menu pMenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getHide() {
        return isHide;
    }

    public void setHide(Boolean hide) {
        isHide = hide;
    }

    public Menu getpMenu() {
        return pMenu;
    }

    public void setpMenu(Menu pMenu) {
        this.pMenu = pMenu;
    }
}
