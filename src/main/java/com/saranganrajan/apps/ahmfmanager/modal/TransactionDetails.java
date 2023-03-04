package com.saranganrajan.apps.ahmfmanager.modal;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {
    private String transactionId;
    private String serviceId;
    private String StatusId;
    private LocalDate transactionDate;
}
