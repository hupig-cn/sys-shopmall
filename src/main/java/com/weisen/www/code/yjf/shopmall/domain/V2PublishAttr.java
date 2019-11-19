package com.weisen.www.code.yjf.shopmall.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A V2PublishAttr.
 */
@Entity
@Table(name = "v_2_publish_attr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class V2PublishAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "form_required")
    private Long formRequired;

    @Column(name = "widget_type")
    private String widgetType;

    @Column(name = "widget_name")
    private String widgetName;

    @Column(name = "ctime")
    private String ctime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public V2PublishAttr name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFormRequired() {
        return formRequired;
    }

    public V2PublishAttr formRequired(Long formRequired) {
        this.formRequired = formRequired;
        return this;
    }

    public void setFormRequired(Long formRequired) {
        this.formRequired = formRequired;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public V2PublishAttr widgetType(String widgetType) {
        this.widgetType = widgetType;
        return this;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public V2PublishAttr widgetName(String widgetName) {
        this.widgetName = widgetName;
        return this;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public String getCtime() {
        return ctime;
    }

    public V2PublishAttr ctime(String ctime) {
        this.ctime = ctime;
        return this;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof V2PublishAttr)) {
            return false;
        }
        return id != null && id.equals(((V2PublishAttr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "V2PublishAttr{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", formRequired=" + getFormRequired() +
            ", widgetType='" + getWidgetType() + "'" +
            ", widgetName='" + getWidgetName() + "'" +
            ", ctime='" + getCtime() + "'" +
            "}";
    }
}
