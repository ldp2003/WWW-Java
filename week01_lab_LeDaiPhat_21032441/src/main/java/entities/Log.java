package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "log", schema = "mydb")
@NamedQueries({
        @NamedQuery(name = "Log.findByAccountIdAndLogoutTime", query = "select l from Log l where l.accountId = :accountId and l.logoutTime = :logoutTime")
})
public class Log {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @NotNull
    @Column(name = "login_time", nullable = false)
    private Instant loginTime;

    @NotNull
    @Column(name = "logout_time", nullable = false)
    private Instant logoutTime;

    @Size(max = 250)
    @NotNull
    @Column(name = "notes", nullable = false, length = 250)
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public Instant getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Instant logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}