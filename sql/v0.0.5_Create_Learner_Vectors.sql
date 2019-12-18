CREATE TABLE learner_vectors(
  id bigserial NOT NULL primary key,
  user_id text NOT NULL, 
  tx_subject_code text NOT NULL,
  tx_domain_code text NOT NULL,
  tx_comp_code text NOT NULL,
  authority integer NOT NULL DEFAULT 0, 
  citizenship integer NOT NULL DEFAULT 0, 
  reputation integer NOT NULL DEFAULT 0, 
  grit integer NOT NULL DEFAULT 0, 
  perseverance integer NOT NULL DEFAULT 0, 
  motivation integer NOT NULL DEFAULT 0, 
  self_confidence integer NOT NULL DEFAULT 0,
  updated_at timestamp without time zone DEFAULT timezone('UTC' :: text, now()) NOT NULL
);
CREATE  INDEX learner_vectors_txs_u_idx ON learner_vectors USING btree (
 user_id,tx_subject_code, tx_domain_code, tx_comp_code
);

