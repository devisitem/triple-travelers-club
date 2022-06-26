package io.taech.triple.business.events.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "KIND_OF")
public abstract class CommonFile {

    @Id
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileExtension;

    @Column(nullable = false)
    private LocalDateTime createTime;

    public void setFileData(final Long size, final String filePath, final String fileName, final String fileExtension) {
        this.size = size;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

}
