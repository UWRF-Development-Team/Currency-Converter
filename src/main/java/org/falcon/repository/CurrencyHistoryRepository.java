package org.falcon.repository;

import org.falcon.entity.currency.CurrencyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyHistoryRepository extends JpaRepository<CurrencyHistory, Long> {

}
