import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationInfo {
    String login;
    String password;
    String status;
}
