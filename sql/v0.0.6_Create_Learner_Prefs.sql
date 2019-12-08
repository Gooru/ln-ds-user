CREATE TABLE learner_prefs(
id bigserial NOT NULL primary key,
user_id text NOT NULL,
tx_subject_code text NOT NULL,
tx_domain_code text NOT NULL,
video_pref integer NOT NULL DEFAULT 0,
webpage_pref integer NOT NULL DEFAULT 0,
interactive_pref integer NOT NULL DEFAULT 0,
image_pref integer NOT NULL DEFAULT 0,
text_pref integer NOT NULL DEFAULT 0,
audio_pref integer NOT NULL DEFAULT 0,
updated_at timestamp without time zone DEFAULT timezone('UTC' :: text, now()) NOT NULL
);

CREATE INDEX learner_prefs_txs_uc_idx ON learner_prefs USING btree (
 user_id, tx_subject_code, tx_domain_code
);

