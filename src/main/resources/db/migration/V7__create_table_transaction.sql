CREATE TABLE transaction (
  transaction_id      UUID PRIMARY KEY,
  type                TEXT NOT NULL CHECK (type IN ('P2P','CARD','FX','TOPUP','WITHDRAW')),
  status              TEXT NOT NULL CHECK (status IN ('PENDING','POSTED','FAILED','REVERSED')),
  debtor_account_id   UUID REFERENCES account(account_id),
  creditor_account_id UUID REFERENCES account(account_id),
  amount              NUMERIC(18,2) NOT NULL,
  currency            CHAR(3) NOT NULL,
  reference           TEXT,
  metadata            JSONB,
  created_at          TIMESTAMPTZ NOT NULL DEFAULT now(),
  posted_at           TIMESTAMPTZ
);