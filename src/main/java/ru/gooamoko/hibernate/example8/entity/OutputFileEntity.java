package ru.gooamoko.hibernate.example8.entity;

import jakarta.persistence.*;
import ru.gooamoko.hibernate.example8.model.FileStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "output_files")
public class OutputFileEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "persons_count", nullable = false)
    private long personCount;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FileStatus status;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getPersonCount() {
        return personCount;
    }

    public void setPersonCount(long personCount) {
        this.personCount = personCount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
