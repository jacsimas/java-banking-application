CREATE TABLE counterparty (
  counterparty_id   UUID PRIMARY KEY,
  customer_id       UUID NOT NULL REFERENCES customer(customer_id),
  display_name      TEXT NOT NULL,
  target_account_id UUID REFERENCES account(account_id),
  external_iban     VARCHAR(34),
  external_bic      VARCHAR(11),
  tags              TEXT[],
  created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT one_target CHECK (
    (target_account_id IS NOT NULL) <> (external_iban IS NOT NULL)
  )
);