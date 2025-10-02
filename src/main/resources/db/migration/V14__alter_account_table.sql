ALTER TABLE account
ADD CONSTRAINT account_customer_fk
FOREIGN KEY (customer_id)
REFERENCES customer(customer_id);