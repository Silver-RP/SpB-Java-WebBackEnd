package demo.devspringboot.WebBackEnd.common.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Data
@NoArgsConstructor
@SuperBuilder // Để lớp con kế thừa
@MappedSuperclass // lớp con kế thừa -> triển khai xuống DB
@EntityListeners(AuditingEntityListener.class) // Cho phép tự khởi tạo một số trường nhất định
public class BaseEntity {
    @Id
    @GeneratedValue
    @Column(name =Columns.ID)
    protected UUID id;

    @Version
    @Column(name =Columns.VERSION)
    protected int version;

    @CreatedBy
    @Column(name =Columns.CREATED_BY)
    protected String createBy;

    @CreatedDate
    @Column(name =Columns.CREATED_AT)
    protected String createAt;

    @LastModifiedBy
    @Column(name =Columns.LASTMODIFIED_BY)
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name =Columns.LASTMODIFIED_AT)
    protected String lastModifiedAt;

    @UtilityClass
    static class Columns{
        public static final String ID = "ID";
        public static final String VERSION = "VERSION";
        public static final String CREATED_AT = "CREATED_AT";
        public static final String CREATED_BY = "CREATED_BY";
        public static final String LASTMODIFIED_AT = "LASTMODIFIED_AT";
        public static final String LASTMODIFIED_BY = "LASTMODIFIED_BY";

    }
}
