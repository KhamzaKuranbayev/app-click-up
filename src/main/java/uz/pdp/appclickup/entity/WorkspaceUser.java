package uz.pdp.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.template.AbstractEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspaceUser extends AbstractEntity {

    @ManyToOne
    private Workspace workspace;

    @ManyToOne
    private User user;

    @ManyToOne
    private WorkspaceRole workspaceRole;

    private Timestamp dateJoined;

}
