package com.example.jeff.axonsample.view;

import com.example.jeff.axonsample.aggregate.contract.ContractInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractView implements ContractInterface {

  @Id
  @Column(length = 64)
  private Long id;

  private String name;

  private String partyA;

  private String partyB;

  private boolean deleted = false;
}
