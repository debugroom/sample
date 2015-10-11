grant select, update, insert, delete on BATCH_JOB_EXECUTION to spring;
grant select, update, insert, delete on BATCH_JOB_EXECUTION_CONTEXT to spring;
grant select, update, insert, delete on BATCH_JOB_EXECUTION_PARAMS to spring;
grant select, update, insert, delete on BATCH_JOB_INSTANCE to spring;
grant select, update, insert, delete on BATCH_STEP_EXECUTION to spring;
grant select, update, insert, delete on BATCH_STEP_EXECUTION_CONTEXT to spring;
grant usage on sequence BATCH_JOB_SEQ to spring;
grant usage on sequence BATCH_JOB_EXECUTION_SEQ to spring;
grant usage on sequence BATCH_STEP_EXECUTION_SEQ to spring;