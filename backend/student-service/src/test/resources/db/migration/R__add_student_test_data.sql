insert into student (id, email, first_name, last_name)
values (nextval('public.hibernate_sequence'), 'mail@mail.com', 'firstName', 'lastName'),
       (nextval('public.hibernate_sequence'), 'mail2@mail.com', 'firstName', 'lastName'),
       (nextval('public.hibernate_sequence'), 'mail3@mail.com', 'firstName', 'lastName'),
       (nextval('public.hibernate_sequence'), 'mail4@mail.com', 'firstName', 'lastName'),
       (nextval('public.hibernate_sequence'), 'mail5@mail.com', 'firstName', 'lastName');
