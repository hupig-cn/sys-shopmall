package com.weisen.www.code.yjf.shopmall.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A V2PublishAttrvalue.
 */
@Entity
@Table(name = "v_2_publish_attrvalue")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class V2PublishAttrvalue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aid")
    private Long aid;

    @Column(name = "title")
    private String title;

    @Column(name = "jhi_value")
    private String value;

    @Column(name = "publish_overwrite")
    private String publishOverwrite;

    @Column(name = "publish_displaysort")
    private String publishDisplaysort;

    @Column(name = "state")
    private Long state;

    @Column(name = "ctime")
    private String ctime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAid() {
        return aid;
    }

    public V2PublishAttrvalue aid(Long aid) {
        this.aid = aid;
        return this;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public V2PublishAttrvalue title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public V2PublishAttrvalue value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPublishOverwrite() {
        return publishOverwrite;
    }

    public V2PublishAttrvalue publishOverwrite(String publishOverwrite) {
        this.publishOverwrite = publishOverwrite;
        return this;
    }

    public void setPublishOverwrite(String publishOverwrite) {
        this.publishOverwrite = publishOverwrite;
    }

    public String getPublishDisplaysort() {
        return publishDisplaysort;
    }

    public V2PublishAttrvalue publishDisplaysort(String publishDisplaysort) {
        this.publishDisplaysort = publishDisplaysort;
        return this;
    }

    public void setPublishDisplaysort(String publishDisplaysort) {
        this.publishDisplaysort = publishDisplaysort;
    }

    public Long getState() {
        return state;
    }

    public V2PublishAttrvalue state(Long state) {
        this.state = state;
        return this;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getCtime() {
        return ctime;
    }

    public V2PublishAttrvalue ctime(String ctime) {
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
        if (!(o instanceof V2PublishAttrvalue)) {
            return false;
        }
        return id != null && id.equals(((V2PublishAttrvalue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "V2PublishAttrvalue{" +
            "id=" + getId() +
            ", aid=" + getAid() +
            ", title='" + getTitle() + "'" +
            ", value='" + getValue() + "'" +
            ", publishOverwrite='" + getPublishOverwrite() + "'" +
            ", publishDisplaysort='" + getPublishDisplaysort() + "'" +
            ", state=" + getState() +
            ", ctime='" + getCtime() + "'" +
            "}";
    }
}
