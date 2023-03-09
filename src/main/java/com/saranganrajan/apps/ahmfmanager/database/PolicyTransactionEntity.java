package com.saranganrajan.apps.ahmfmanager.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "ahmf_policy_transaction")
public class PolicyTransactionEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "transaction_id")
    @JsonProperty("policyTransactionId")
    private String policyTransactionId;

    @JsonProperty("policyNumber")
    @Column(name = "policy_number")
    private String policyNumber;

    @JsonProperty("premiumPaid")
    @Column(name = "premium_paid")
    private Double premiumPaid;

    @JsonProperty("paymentDate")
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @JsonProperty("paymentMode")
    @Column(name = "payment_mode")
    private String paymentMode;

    @JsonProperty("status")
    @Column(name = "status")
    private String status;

}
