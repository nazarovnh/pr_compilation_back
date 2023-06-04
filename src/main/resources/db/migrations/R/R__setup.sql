-- Subject

INSERT INTO subject (subject_id, number_hours, subject_title, subject_description)
VALUES ('d0a1b24d-0522-4e6b-9bd4-eef459032a03', 108, 'Основы программирования',
        'Основы программирования на C/C++: Этот предмет посвящен конкретно программированию на языках C и C++. Студенты узнают о синтаксисе и структуре этих языков, а также об основных понятиях программирования, таких как переменные, типы данных, циклы и условные операторы. Курс также может охватывать более продвинутые темы, такие как указатели, управление памятью и объектно-ориентированное программирование. Цель предмета - дать студентам полное представление о программировании на C и C++, чтобы они могли писать эффективный код на этих языках.')
ON CONFLICT (subject_id) DO NOTHING;

INSERT INTO subject (subject_id, number_hours, subject_title, subject_description)
VALUES ('80dfcf1a-3622-4cb2-8529-d57a56d6efcf', 108, 'Алгоритмизация и программирование',
        'Алгоритмизация и программирование: Этот предмет преподает основы программирования и алгоритмического решения задач. Студенты узнают о типах данных, переменных, управляющих структурах и функциях, а также о том, как использовать их для решения задач с использованием алгоритмов. Курс также может охватывать такие темы, как рекурсия, алгоритмы сортировки и поиска, а также структуры данных, такие как массивы и связанные списки. Цель предмета - дать студентам прочную основу в программировании и алгоритмическом мышлении, на которую они смогут опираться на последующих курсах.')
ON CONFLICT (subject_id) DO NOTHING;

-- Topics

INSERT INTO topic (topic_id, subject_id, topic_title, topic_description, topic_order, threshold_score)
VALUES ('ed6bd2d6-941c-4497-922d-ce68fadd98f5', 'd0a1b24d-0522-4e6b-9bd4-eef459032a03', 'Рекурсия',
        'На этот раз давайте посмотрим, как работать с функциями на C. Давайте изучим рекурсивные алгоритмы, напишем пару полезных функций. Давайте поработаем над передачей переменных через аргументы функции. Давайте разберемся в самой сути циклов.',
        1, 0),
       ('75248c12-46c2-4787-8f0e-d9298d18d965', 'd0a1b24d-0522-4e6b-9bd4-eef459032a03', 'Arrays and Matrices', 'In the practice of algorithm analysis, one of the common tasks is to work with arrays. Arrays can be of different dimensions, ranging from one-dimensional arrays to multi-dimensional arrays. In this particular practice, the focus is on analyzing algorithms for working with one-dimensional arrays and two-dimensional arrays, also known as matrices.
        A one-dimensional array is a data structure that stores a collection of values of the same type, such as integers or floating-point numbers, in a linear sequence. Accessing elements in a one-dimensional array is done using an index, which is an integer value that represents the position of the element in the array.
        A two-dimensional array, on the other hand, is an array that is arranged in rows and columns. It can be visualized as a table, where each cell in the table stores a value of the same type. Accessing elements in a two-dimensional array is done using two indices, one for the row and one for the column.
        In this practice, algorithms for performing various operations on one-dimensional and two-dimensional arrays are analyzed, such as searching for an element, sorting the array, computing the sum or average of the elements, and so on. By analyzing these algorithms, students can gain a deeper understanding of the properties of different algorithms and how to choose the most appropriate algorithm for a given problem.',
        2, 20),

       ('4215bb99-aef8-43a2-a9fb-732319513f8e', '80dfcf1a-3622-4cb2-8529-d57a56d6efcf', 'Sorting',
        'Sorting is a fundamental concept in computer science and is used to arrange a collection of data in a specific order. In the Sorting subject, students learn about different sorting algorithms and their efficiency. They also learn how to analyze algorithms and evaluate their time and space complexity. Some of the popular sorting algorithms taught in this subject include bubble sort, insertion sort, selection sort, merge sort, quicksort, and heapsort. Sorting is an essential skill for any programmer, and understanding the different sorting algorithms can help in solving many real-world problems.',
        1, 0),
       ('911bb21d-7222-47de-98dd-cebc0e839a58', '80dfcf1a-3622-4cb2-8529-d57a56d6efcf', 'Searching',
        'Searching is a fundamental topic in computer science that deals with finding a particular element within a collection of data. Binary search is one of the most common searching algorithms, which works by dividing a sorted array into two halves repeatedly until the target element is found or the entire array has been searched. It has a time complexity of O(log n), making it much faster than linear search for large arrays. Binary search is widely used in various applications, such as databases, file systems, and network routing.',
        2, 20)
ON CONFLICT (topic_id) DO NOTHING;

-- Task

INSERT INTO task (task_id, topic_id, task_title, task_description, task_order, time_limit, memory_limit, task_price)
VALUES ('643ed0d2-9630-11ec-b909-0242ac120002', 'ed6bd2d6-941c-4497-922d-ce68fadd98f5', 'Recursive exponentiation', 'Write a program that raises a number to an integer power using recursion.
        It is forbidden to use loops (for, while, do-while, goto labels). Use recursion instead.', 1, 10000, 256, 10),
       ('84f4336b-6f7d-4e6b-8121-d5a41326bb3d', 'ed6bd2d6-941c-4497-922d-ce68fadd98f5', 'Recursive Fibonacci',
        'It is forbidden to use loops (for, while, do-while, goto labels). Use recursion instead.
        Write a recursive function that calculates the nth character of the Fibonacci sequence. Test the program on different data.',
        2, 10000, 256, 10),

       ('e15ec50a-a3e8-4b45-9068-e68b8a331bfc', '75248c12-46c2-4787-8f0e-d9298d18d965', 'Anagram',
        'Write a function that checks whether two words are an anagram. Each character of the word is a `char''.
        Two words are an anagram if the second word is obtained by simply rearranging the letters of the first word. For example, `listen` and `silent'' are anagrams.
        It is said that the counting sorting algorithm can be used to determine anagrams.', 1, 10000, 256, 10),
       ('3db6e487-d8cd-4cd3-a745-831b33eaad0f', '75248c12-46c2-4787-8f0e-d9298d18d965', 'Maximum in a row',
        'Develop a program to count the maximum number
         of consecutive identical array elements. Fill an array of size 10 with numbers from the console. Example, for 1 1 2 2 5 5 5 4 1 1 answer 3.', 2, 10000, 256, 10),

       ('ae343db0-056b-496d-8aa7-f9ced2b04fa9', '4215bb99-aef8-43a2-a9fb-732319513f8e', 'Insertions sort',
        'An array of numbers is given. Sort in ascending order by the insertion sorting algorithm.', 1, 10000, 256, 10),
       ('81405c4a-ccf4-4aa9-a055-a6cf15032534', '4215bb99-aef8-43a2-a9fb-732319513f8e', 'Merge sort',
        'An array of numbers is given. Sort in ascending order by the merge sorting algorithm.', 2, 10000, 256, 10),

       ('df8450a3-519e-4e56-9fbf-bcd35099ce91', '911bb21d-7222-47de-98dd-cebc0e839a58', 'Binary search',
        'An ascending array of numbers is given. Given a second array of numbers — queries. For each request, it is necessary to output the number from the first array closest to the requested one.', 1, 10000, 256, 10),
       ('7de213ec-fced-4e19-a0d5-24f5349213f5', '911bb21d-7222-47de-98dd-cebc0e839a58', 'Jump search',
        'An array of numbers is given. Find value by number', 2, 10000, 256, 10)
ON CONFLICT (task_id) DO NOTHING;

-- Test case

INSERT INTO test_case (test_case_id, task_id, input, correct_output)
-- Power of number
VALUES ('4e8271df-9688-4e16-8c88-247cceca20b1', '643ed0d2-9630-11ec-b909-0242ac120002', '5 2', '25'),
        ('5e9610d0-6b9e-4a9b-952d-4b96d22af17f', '643ed0d2-9630-11ec-b909-0242ac120002', '2 5', '32'),

-- Fibonacci
        ('a7d48c07-eb37-44ce-90c9-9199c5d67bda', '84f4336b-6f7d-4e6b-8121-d5a41326bb3d', '10', '0 1 1 2 3 5 8 13 21 34'),
        ('173308ba-b0af-4924-8842-3cb7a8f0d20d', '84f4336b-6f7d-4e6b-8121-d5a41326bb3d', '15', '0 1 1 2 3 5 8 13 21 34 55 89 144 233 377'),

-- Anagram
        ('86c9e14f-2baf-4b59-8a3b-39a38f551e2f', 'e15ec50a-a3e8-4b45-9068-e68b8a331bfc', 'silent', 'listen'),
        ('4f684ded-2f21-4a6f-8425-5d84474f5bd7', 'e15ec50a-a3e8-4b45-9068-e68b8a331bfc', 'pepal', 'apple'),

-- Max in row
        ('ab6d5af5-76dc-40ea-8980-e979bb606fe1', '3db6e487-d8cd-4cd3-a745-831b33eaad0f', '1 1 2 2 5 5 5 4 1 1', '3'),
        ('4bb1872e-b2e3-406f-a464-53824a341bfa', '3db6e487-d8cd-4cd3-a745-831b33eaad0f', '1 1 0 1 0 0 0 1 1 1', '3'),

-- Insertion sort
        ('816907bb-1851-4eea-a9b7-ab5418ec744d', 'ae343db0-056b-496d-8aa7-f9ced2b04fa9', '10 1 2 5 1 1 2 6 8 9 3', '1 1 1 2 2 3 5 6 8 9'),
        ('23ece79c-2cfd-4d61-aa5f-9d36e3211e43', 'ae343db0-056b-496d-8aa7-f9ced2b04fa9', '5 6 12 11 5 13', '5 6 11 12 13'),

-- Merge sort
        ('769aca9c-a995-4708-853d-ed40ad5c0c68', '81405c4a-ccf4-4aa9-a055-a6cf15032534', '10 1 2 5 1 1 2 6 8 9 3', '1 1 1 2 2 3 5 6 8 9'),
        ('9287dd84-8e9e-4807-829d-48ec87eb7689', '81405c4a-ccf4-4aa9-a055-a6cf15032534', '5 6 12 11 5 13', '5 6 11 12 13'),

-- Binary search
        ('528b264e-54d3-4119-974b-cd196ff140a4', 'df8450a3-519e-4e56-9fbf-bcd35099ce91', '10 5 2 3 4 10 40', '3'),
        ('c42e9b6a-638a-4fd9-acf3-7d9668b705c0', 'df8450a3-519e-4e56-9fbf-bcd35099ce91', '10 10 1 2 3 4 5 6 7 8 9 10', '9'),

-- Jump search
        ('77a111b4-833c-4827-bf6c-fa05add04058', '7de213ec-fced-4e19-a0d5-24f5349213f5', '10 5 2 3 4 10 40', '3'),
        ('c705ffa6-4fdd-4226-abe4-ab78353b4c48', '7de213ec-fced-4e19-a0d5-24f5349213f5', '10 10 1 2 3 4 5 6 7 8 9 10', '9')
ON CONFLICT (test_case_id) DO NOTHING;

-- Users

INSERT INTO users (user_id, email, password, enabled)
VALUES ('38d467d5-502f-4e28-b84c-fcd3657f2352', 'teacher@example.com', '$2a$10$hXfhu8d76Gm0lYhrP4Fpnu00Gz4WOUtjIZRUd/I66GEjNX7eciMj.', true),
('c92de672-b176-4589-bfb3-9b37355a6d32', 'ivan@example.com', '$2a$10$bcJdN4wF3jlUt2m9L1Wyze0V9ONIsW5NvYN1wSPafiDYCA4Ei9AIW', true),
('009f10b6-7b3b-4743-a5e0-def55ce191ab', 'roman@example.com', '$2a$10$mwkC8gZ7kK0itCWjCXsakuq9.l14pYw26RM5UU1yFbdGiByXhJ426', true),
('05d5b285-6393-4f65-a69b-55a12f3258c0', 'nikita@example.com', '$2a$10$WeEcOmx7TxKAVA3Cm2g5ROzgYUH9HiY/rAev4IeFE6NFIBILSmwua', true)
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO user_roles(user_id, role)
VALUES ('38d467d5-502f-4e28-b84c-fcd3657f2352', 'TEACHER'),
('38d467d5-502f-4e28-b84c-fcd3657f2352', 'USER'),
('c92de672-b176-4589-bfb3-9b37355a6d32', 'USER'),
('009f10b6-7b3b-4743-a5e0-def55ce191ab', 'USER'),
('05d5b285-6393-4f65-a69b-55a12f3258c0', 'USER') ON CONFLICT (user_id, role) DO NOTHING;

-- Study group

INSERT INTO study_group(study_group_id, study_group_name)
VALUES ('338f9c66-4279-4830-badf-43ce6ce20465', 'MIB-901') ON CONFLICT (study_group_id) DO NOTHING;

INSERT INTO study_group_user(user_id, study_group_id)
VALUES ('c92de672-b176-4589-bfb3-9b37355a6d32', '338f9c66-4279-4830-badf-43ce6ce20465'),
('009f10b6-7b3b-4743-a5e0-def55ce191ab', '338f9c66-4279-4830-badf-43ce6ce20465'),
('05d5b285-6393-4f65-a69b-55a12f3258c0', '338f9c66-4279-4830-badf-43ce6ce20465');
