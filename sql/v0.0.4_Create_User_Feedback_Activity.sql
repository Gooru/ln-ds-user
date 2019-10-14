CREATE TABLE user_activity_feedback(id bigserial NOT NULL, 
content_id text NOT NULL,
content_type text NOT NULL,
user_id text NOT NULL,
user_category_id smallint NOT NULL,
feedback_category_id integer,
user_feedback_quantitative smallint,
user_feedback_qualitative text,
created_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
CONSTRAINT uaf_pkey PRIMARY KEY (id),
CONSTRAINT content_type_check CHECK (content_type = ANY(ARRAY['collection', 'assessment','resource','question','assessment-external','offline-activity','collection-external','course'])),
CONSTRAINT user_category_id_check CHECK (user_category_id = ANY(ARRAY[1,2,3]))
);

CREATE INDEX user_activity_feedback_cuf_idx ON user_activity_feedback USING btree (content_id, user_id);
