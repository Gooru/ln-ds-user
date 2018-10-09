create table grade_master (
	id bigserial PRIMARY KEY,
	grade text NOT NULL,
	description text,
	thumbnail text,
	tx_subject_code text NOT NULL,
	grade_seq int NOT NULL,
	created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	UNIQUE (tx_subject_code, grade)
);

INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade K', 'K12.MA', 1);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 1', 'K12.MA', 2);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 2', 'K12.MA', 3);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 3', 'K12.MA', 4);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 4', 'K12.MA', 5);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 5', 'K12.MA', 6);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 6', 'K12.MA', 7);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 7', 'K12.MA', 8);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 8', 'K12.MA', 9);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Algebra 1', 'K12.MA', 10);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Geometry', 'K12.MA', 11);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Algebra 2', 'K12.MA', 12);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Pre-calculus', 'K12.MA', 13);

update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>Counting and Cardinality</b><ul><li>Know number names and the count sequence.</li><li>Count to tell the number of objects.</li><li>Compare numbers.</li></ul><b>Operations and Algebraic Thinking</b><ul><li>Understand addition as putting together and adding to, and understand subtraction as taking apart and taking from.</li></ul><b>Number and Operations in Base Ten</b><ul><li>Work with numbers 11-19 to gain foundations for place value.</li></ul><b>Measurement and Data</b><ul><li>Describe and compare measurable attributes.</li><li>Classify objects and count the number of objects in each category</li></ul><b>Geometry</b><ul><li>Identify and describe shapes.</li><li>Analyze, compare, create, and compose shapes.</li></ul><b>Mathematical Practices</b><ol><li>Make sense of problems and persevere in solving them.</li><li>Reason abstractly and quantitatively.</li><li>Construct viable arguments and critique the reasoning of others.</li><li>Model with mathematics.</li><li>Use appropriate tools strategically.</li><li>Attend to precision.</li><li>Look for and make use of structure.</li><li>Look for and express regularity in repeated reasoning.</li></ol>' where id = 1;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts  <b>Operations and Algebraic Thinking</b><ul><li>Represent and solve problems involving addition and subtraction.</li><li>Understand and apply properties of operations and the relationship between addition and subtraction.</li><li>Add and subtract within 20.</li><li>Work with addition and subtraction equations.</li></ul><b>Number and Operations in Base Ten</b><ul><li>Extend the counting sequence.</li><li>Understand place value.</li><li>Use place value understanding and properties of operations to add and subtract.</li></ul><b>Measurement and Data</b><ul><li>Measure lengths indirectly and by iterating length units.</li><li>Tell and write time.</li><li>Represent and interpret data.</li></ul><b>Geometry</b><ul><li>Reason with shapes and their attributes.</li></ul>' where id = 2;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>Operations and Algebraic Thinking</b><ul><li>Represent and solve problems involving addition and subtraction.</li><li>Add and subtract within 20.</li><li>Work with equal groups of objects to gain foundations for multiplication.</li></ul><b>Number and Operations in Base Ten</b><ul><li>Understand place value.</li><li>Use place value understanding and properties of operations to add and subtract.</li></ul><b>Measurement and Data</b><ul><li>Measure and estimate lengths in standard units.</li><li>Relate addition and subtraction to length.</li><li>Work with time and money.</li><li>Represent and interpret data.</li></ul><b>Geometry</b><ul><li>Reason with shapes and their attributes.</li></ul>' where id = 3;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>Operations and Algebraic Thinking</b><ul><li>Represent and solve problems involving multiplication and division.</li><li>Understand properties of multiplication and the relationship between multiplication and division.</li><li>Multiply and divide within 100.</li><li>Solve problems involving the four operations, and identify and explain patterns in arithmetic.</li></ul><b>Number and Operations in Base Ten</b><ul><li>Use place value understanding and properties of operations to perform multi-digit arithmetic.</li></ul><b>Number and Operations—Fractions</b><ul><li>Develop understanding of fractions as numbers.</li></ul><b>Measurement and Data</b><ul><li>Solve problems involving measurement and estimation of intervals of time, liquid volumes, and masses of objects.</li><li>Represent and interpret data.</li><li>Geometric measurement: understand concepts of area and relate area to multiplication and to addition.</li><li>Geometric measurement: recognize perimeter as an attribute of plane figures and distinguish between linear and area measures.</li></ul><b>Geometry</b><ul><li>Reason with shapes and their attributes.</li></ul>' where id = 4;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>Operations and Algebraic Thinking</b><ul><li>Use the four operations with whole numbers to solve problems.</li><li>Gain familiarity with factors and multiples.</li><li>Generate and analyze patterns.</li></ul><b>Number and Operations in Base Ten</b><ul><li>Generalize place value understanding for multi-digit whole numbers.</li><li>Use place value understanding and properties of operations to perform multi-digit arithmetic.</li></ul><b>Number and Operations—Fractions</b><ul><li>Extend understanding of fraction equivalence and ordering.</li><li>Build fractions from unit fractions by applying and extending previous understandings of operations on whole numbers.</li><li>Understand decimal notation for fractions, and compare decimal fractions.</li></ul><b>Measurement and Data</b><ul><li>Solve problems involving measurement and conversion of measurements from a larger unit to a smaller unit.</li><li>Represent and interpret data.</li><li>Geometric measurement: understand concepts of angle and measure angles.</li></ul><b>Geometry</b><ul><li>Draw and identify lines and angles, and classify shapes by properties of their lines and angles.</li></ul>' where id = 5;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>Operations and Algebraic Thinking</b><ul><li>Write and interpret numerical expressions.</li><li>Analyze patterns and relationships.</li></ul><b>Number and Operations in Base Ten</b><ul><li>Understand the place value system.</li><li>Perform operations with multi-digit whole numbers and with decimals to hundredths.</li></ul><b>Number and Operations—Fractions</b><ul><li>Use equivalent fractions as a strategy to add and subtract fractions.</li><li>Apply and extend previous understandings of multiplication and division to multiply and divide fractions.</li></ul><b>Measurement and Data</b><ul><li>Convert like measurement units within a given measurement system.</li><li>Represent and interpret data.</li><li>Geometric measurement: understand concepts of volume and relate volume to multiplication and to addition.</li></ul><b>Geometry</b><ul><li>Graph points on the coordinate plane to solve real-world and mathematical problems.</li><li>Classify two-dimensional figures into categories based on their properties.</li></ul>' where id = 6;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts <b>Ratios and Proportional Relationships</b><ul><li>Understand ratio concepts and use ratio reasoning to solve problems.</li></ul><b>The Number System</b><ul><li>Apply and extend previous understandings of multiplication and division to divide fractions by fractions.</li><li>Multiply and divide multi-digit numbers and find common factors and multiples.</li><li>Apply and extend previous understandings of numbers to the system of rational numbers.</li></ul><b>Expressions and Equations</b><ul><li>Apply and extend previous understandings of arithmetic to algebraic expressions.</li><li>Reason about and solve one-variable equations and inequalities.</li><li>Represent and analyze quantitative relationships between dependent and independent variables.</li></ul><b>Geometry</b><ul><li>Solve real-world and mathematical problems involving area, surface area, and volume.</li></ul><b>Statistics and Probability</b><ul><li>Develop understanding of statistical variability.</li><li>Summarize and describe distributions.</li></ul>' where id = 7;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>Ratios and Proportional Relationships</b><ul><li>Analyze proportional relationships and use them to solve real-world and mathematical problems.</li></ul><b>The Number System</b><ul><li>Apply and extend previous understandings of operations with fractions to add, subtract, multiply, and divide rational numbers.</li></ul><b>Expressions and Equations</b><ul><li>Use properties of operations to generate equivalent expressions.</li><li>Solve real-life and mathematical problems using numerical and algebraic expressions and equations.</li></ul><b>Geometry</b><ul><li>Draw, construct and describe geometrical figures and describe the relationships between them.</li><li>Solve real-life and mathematical problems involving angle measure, area, surface area, and volume.</li></ul><b>Statistics and Probability</b><ul><li>Use random sampling to draw inferences about a population.</li><li>Draw informal comparative inferences about two populations.</li><li>Investigate chance processes and develop, use, and evaluate probability models.</li></ul>' where id = 8;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>The Number System</b><ul><li>Know that there are numbers that are not rational, and approximate them by rational numbers.</li></ul><b>Expressions and Equations</b><ul><li>Work with radicals and integer exponents.</li><li>Understand the connections between proportional relationships, lines, and linear equations.</li><li>Analyze and solve linear equations and pairs of simultaneous linear equations.</li></ul><b>Functions</b><ul><li>Define, evaluate, and compare functions.</li><li>Use functions to model relationships between quantities.</li></ul><b>Geometry</b><ul><li>Understand congruence and similarity using physical models, transparencies, or geometry software.</li><li>Understand and apply the Pythagorean Theorem.</li><li>Solve real-world and mathematical problems involving volume of cylinders, cones and spheres.</li></ul><b>Statistics and Probability</b><ul><li>Investigate patterns of association in bivariate data.</li></ul>' where id = 9;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>Seeing Structure in Expressions</b><ul><li>Interpret the structure of expressions</li><li>Write expressions in equivalent forms to solve problems</li></ul><b>Arithmetic with Polynomials and Rational Functions</b><ul><li>Perform arithmetic operations on polynomials</li><li>Understand the relationship between zeros and factors of polynomials</li><li>Use polynomial identities to solve problems</li><li>Rewrite rational functions</li></ul><b>Creating Equations</b><ul><li>Create equations that describe numbers or relationships</li></ul><b>Reasoning with Equations and Inequalities</b><ul><li>Understand solving equations as a process of reasoning and explain the reasoning</li><li>Solve equations and inequalities in one variable</li><li>Solve systems of equations</li><li>Represent and solve equations and inequalities graphically</li></ul>' where id = 10;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts<b>Congruence</b><ul><li>Experiment with transformations in the plane</li><li>Understand congruence in terms of rigid motions</li><li>Prove geometric theorems</li><li>Make geometric constructions</li></ul><b>Similarity, Right Triangles, and Trigonometry</b><ul><li>Understand similarity in terms of similarity transformations</li><li>Prove theorems involving similarity</li><li>Define trigonometric ratios and solve problems involving right triangles</li><li>Apply trigonometry to general triangles</li></ul><b>Circles</b><ul><li>Understand and apply theorems about circles</li><li>Find arc lengths and areas of sectors of circles</li></ul><b>Expressing Geometric Properties with Equations</b><ul><li>Translate between the geometric description and the equation for a conic section</li><li>Use coordinates to prove simple geometric theorems algebraically</li></ul><b>Geometric Measurement and Dimension</b><ul><li>Explain volume formulas and use them to solve problems</li><li>Visualize relationships between two-dimensional and three-dimensional objects</li></ul><b>Modeling with Geometry</b><ul><li>Apply geometric concepts in modeling situations</li></ul>' where id = 11;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts' where id = 12;
update grade_master set description = 'When you reach this destination, you would have learnt the following topics and concepts' where id = 13;

create table grade_competency_bound (
	id bigserial PRIMARY KEY,
	grade_id bigint references grade_master(id),
	tx_subject_code text NOT NULL,
	tx_domain_code text NOT NULL,
	highline_tx_comp_code text,
	lowline_tx_comp_code text,
	created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	UNIQUE (tx_subject_code, tx_domain_code, grade_id)
);

INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'OAT', 'K12.MA-MAK-OAT-A.05', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'OAT', 'K12.MA-MA1-OAT-D.02', 'K12.MA-MAK-OAT-A.05');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'OAT', 'K12.MA-MA2-OAT-C.02', 'K12.MA-MA1-OAT-D.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'OAT', 'K12.MA-MA3-OAT-D.02', 'K12.MA-MA2-OAT-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'OAT', 'K12.MA-MA4-OAT-C.01', 'K12.MA-MA3-OAT-D.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA4-OAT-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'NOBT', 'K12.MA-MAK-NOBT-A.01', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'NOBT', 'K12.MA-MA1-NOBT-C.03', 'K12.MA-MAK-NOBT-A.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'NOBT', 'K12.MA-MA2-NOBT-B.05', 'K12.MA-MA1-NOBT-C.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'NOBT', 'K12.MA-MA3-NOBT-A.03', 'K12.MA-MA2-NOBT-B.05');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'NOBT', 'K12.MA-MA4-NOBT-B.03', 'K12.MA-MA3-NOBT-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA4-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'NOF', 'K12.MA-MA3-NOF-A.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'NOF', 'K12.MA-MA4-NOF-C.03', 'K12.MA-MA3-NOF-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA4-NOF-C.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'MD', 'K12.MA-MAK-MD-B.01', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'MD', 'K12.MA-MA1-MD-C.01', 'K12.MA-MAK-MD-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'MD', 'K12.MA-MA2-MD-D.02', 'K12.MA-MA1-MD-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'MD', 'K12.MA-MA3-MD-D.01', 'K12.MA-MA2-MD-D.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'MD', 'K12.MA-MA4-MD-C.03', 'K12.MA-MA3-MD-D.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA4-MD-C.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'G', 'K12.MA-MAK-G-B.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'G', 'K12.MA-MA1-G-A.03', 'K12.MA-MAK-G-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'G', 'K12.MA-MA2-G-A.03', 'K12.MA-MA1-G-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'G', 'K12.MA-MA3-G-A.02', 'K12.MA-MA2-G-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'G', 'K12.MA-MA4-G-A.03', 'K12.MA-MA3-G-A.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'G', 'K12.MA-MA5-G-B.02', 'K12.MA-MA4-G-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'G', 'K12.MA-MA6-G-A.04', 'K12.MA-MA5-G-B.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'G', 'K12.MA-MA7-G-B.03', 'K12.MA-MA6-G-A.04');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'G', 'K12.MA-MA8-G-C.01', 'K12.MA-MA7-G-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'G', 'K12.MA-MA8-G-C.01', 'K12.MA-MA8-G-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'G', 'K12.MA-GEO-G-MG.A.03', 'K12.MA-MA8-G-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'G', 'K12.MA-GEO-G-MG.A.03', 'K12.MA-GEO-G-MG.A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'G', 'K12.MA-PC-G-GMD.A.02', 'K12.MA-GEO-G-MG.A.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'RPS', 'K12.MA-MA6-RPS-A.03.04', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA6-RPS-A.03.04');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'TNS', 'K12.MA-MA6-TNS-C.04', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'TNS', 'K12.MA-MA7-TNS-A.03', 'K12.MA-MA6-TNS-C.04');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA7-TNS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA8-TNS-A.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA8-TNS-A.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA8-TNS-A.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA8-TNS-A.02');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'EE', 'K12.MA-MA6-EE-C.01', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'EE', 'K12.MA-MA7-EE-B.02.02', 'K12.MA-MA6-EE-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA7-EE-B.02.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA8-EE-C.02.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA8-EE-C.02.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA8-EE-C.02.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA8-EE-C.02.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'NQ', 'K12.MA-A1-NQ-Q.A.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'NQ', 'K12.MA-A1-NQ-Q.A.03', 'K12.MA-A1-NQ-Q.A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'NQ', 'K12.MA-A2-NQ-CN.C.03', 'K12.MA-A1-NQ-Q.A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'NQ', 'K12.MA-PC-NQ-VM.C.12', 'K12.MA-A2-NQ-CN.C.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'A', 'K12.MA-A1-A-REI.D.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'A', 'K12.MA-A1-A-REI.D.03', 'K12.MA-A1-A-REI.D.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'A', 'K12.MA-A2-A-REI.D.01', 'K12.MA-A1-A-REI.D.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'A', 'K12.MA-PC-A-REI.C.09', 'K12.MA-A2-A-REI.D.01');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'F', 'K12.MA-A1-F-LE.B.01', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'F', 'K12.MA-A1-F-LE.B.01', 'K12.MA-A1-F-LE.B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'F', 'K12.MA-A2-F-TF.C.01', 'K12.MA-A1-F-LE.B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'F', 'K12.MA-PC-F-TF.B.07', 'K12.MA-A2-F-TF.C.01');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'SP', 'K12.MA-A1-SP-ID.C.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'SP', 'K12.MA-GEO-SP-MD.B.02', 'K12.MA-A1-SP-ID.C.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'SP', 'K12.MA-A2-SP-MD.B.02', 'K12.MA-GEO-SP-MD.B.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'SP', 'K12.MA-PC-SP-MD.B.05', 'K12.MA-A2-SP-MD.B.02');
