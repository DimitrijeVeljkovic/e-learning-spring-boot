package com.dveljkovic.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="paragraph")
public class Paragraph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="paragraph_id")
    private int paragraphId;

    @Column(name="data")
    private String data;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="section_id")
    @JsonIgnore
    private Section section;

    public Paragraph() {

    }

    public Paragraph(String data) {
        this.data = data;
    }

    public int getParagraphId() {
        return paragraphId;
    }

    public void setParagraphId(int paragraphId) {
        this.paragraphId = paragraphId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
