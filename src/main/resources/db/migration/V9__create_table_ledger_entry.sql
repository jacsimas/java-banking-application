CREATE TABLE ledger_entry (
  entry_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  transaction_id  UUID    NOT NULL REFERENCES transaction(transaction_id),
  account_id      UUID    NOT NULL REFERENCES account(account_id),
  currency        CHAR(3) NOT NULL,
  amount          NUMERIC(18,2) NOT NULL,         -- +CR, -DR
  side            TEXT GENERATED ALWAYS AS (
                    CASE WHEN amount >= 0 THEN 'CR' ELSE 'DR' END
                  ) STORED,                        -- derived label
  event_time      TIMESTAMPTZ NOT NULL DEFAULT now(),
  description     TEXT,
  metadata        JSONB
);

CREATE INDEX idx_ledger_account_time ON ledger_entry (account_id, event_time);
CREATE INDEX idx_ledger_tx           ON ledger_entry (transaction_id);