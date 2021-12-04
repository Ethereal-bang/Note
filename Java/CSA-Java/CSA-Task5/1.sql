<<<<<<< HEAD
ALTER DATABASE `task5` CHARACTER SET utf8;

# 题1
CREATE TABLE `cqupt student`(
  `studentid` VARCHAR(10),
  `name` VARCHAR(20),
  `sex` VARCHAR(2),
  `age` INT,
  `Fee` DECIMAL(10, 2),
  `address` VARCHAR(50),
  `memo` VARCHAR(300)
);

# 题2
CREATE TABLE `courseAa`(
  `Aa1`	VARCHAR(20),
  `Aa2`	INT,
  `Aa3`	DECIMAL(10)
);

# 题3
CREATE TABLE `ChooseBb`(
  `Bb1` VARCHAR(30),
  `Bb2`	INT,
  `Bb3`	DECIMAL(6)
);
# 题4
ALTER DATABASE `task5` CHARACTER SET utf8; 
ALTER TABLE `choosebb` ADD `Bb4` VARCHAR(20) NOT NULL DEFAULT '系统测试值';
# 题5
ALTER TABLE `choosebb` MODIFY `bb1` DECIMAL(10);
ALTER TABLE `choosebb` MODIFY `bb2` CHAR(6);
ALTER TABLE `choosebb` MODIFY `bb1` INT(11);
ALTER TABLE `choosebb` MODIFY `bb1` VARCHAR(30);
ALTER TABLE `choosebb` ADD `Bb5` VARCHAR(30) NOT NULL PRIMARY KEY;
# 题6
CREATE VIEW `View_Choosebb`(
`View_bb1`, `View_bb2`, `view_bb3`)
AS SELECT `bb1`, `bb4`, `bb5` FROM `choosebb`;
# 题7
DROP VIEW `View_Choosebb`;	
# 题8
CREATE INDEX `Index_bb2` ON `choosebb`(`bb2`);
CREATE INDEX `Index_bb4` ON `choosebb`(`Bb4` DESC);
# 题9
DROP INDEX `index_bb2` ON `choosebb`;

# 题10
CREATE TABLE `test`(
`name` VARCHAR(20),
`age` INT,
`score` NUMERIC(10, 2),	-- 总长10位，小数长2位
`address` VARCHAR(60)
);
# 题11
INSERT INTO `test` VALUES ('赵一', 20, 580.00, '重邮宿舍 12-3-5');
INSERT INTO test VALUES
('钱二', 19, 540.00, '南海苑 5-2-9'),
('孙三', 21, 555.50, '学生新区 21-5-15'),
('李四', 22, 505.00, '重邮宿舍 8-6-22'),
('周五', 20, 495.50, '学生新区 23-4-8'),
('吴六', 19, 435.00, '南海苑 2-5-12');
# 题12
CREATE TABLE test_temp (
`name` VARCHAR(20),
`age` INT,
score NUMERIC(10, 2),
address VARCHAR(60)
);
# 题13
INSERT INTO test_temp VALUES
('郑七', 19, 540.00, '重邮宿舍 11-2-1'),
('张八', 21, 555.50, '南海苑 3-3-3'),
('王九', 22, 505.00, '学生新区 19-7-1');
# 题14
INSERT INTO test SELECT * FROM test_temp;
# 题15
UPDATE test SET score=score+5 WHERE age<=20;
# 题16
UPDATE test SET age=age-1 WHERE address LIKE '南海苑%';
# 题17
DELETE FROM test WHERE age>=21 AND score>=500;
# 题18
DELETE FROM test WHERE score<550 AND address LIKE '重邮宿舍%';

# 题19
CREATE TABLE Student(
SNO VARCHAR(20),
`name` VARCHAR(10),
age INT,
college VARCHAR(30)
);
# 题20
CREATE TABLE Course(
CourseID VARCHAR(15),
CourseName VARCHAR(30),
CourseBeforeID VARCHAR(15)
);
# 题21
CREATE TABLE Choose(
SNO VARCHAR(20),
CourseID VARCHAR(30),
Score DECIMAL(5, 2));
# 题22
INSERT INTO student VALUES
('S00001', '张三', 20, '计算机学院'),
('S00002', '李四', 19, '通信学院'),
('S00003', '王五', 21, '计算机学院');
# 题23
INSERT INTO course VALUES
('C1', '计算机引论', NULL),
('C2', 'C 语言', 'c1'),
('C3', '数据结构', 'c2');
# 题24
INSERT INTO choose VALUES
('S00001', 'C1', 95),
('S00001', 'C2', 80),
('S00001', 'C3', 84),
('S00002', 'C1', 80),
('S00002', 'C2', 85),
('S00003', 'C1', 78),
('S00003', 'C3', 70);

# 题25
SELECT sno, `name` FROM student;
# 题26
SELECT * FROM student WHERE age BETWEEN 20 AND 23;
# 题27
SELECT COUNT(*) FROM student;
# 题28
SELECT MAX(score) FROM choose;
SELECT MIN(score) FROM choose;
SELECT SUM(score) FROM choose;
SELECT AVG(score) FROM choose; 
# 题29
SELECT CourseID, CourseName FROM course WHERE CourseBeforeID IS NULL;
# 题30
SELECT stu.`SNO`, stu.`name`, course.`CourseName`, choose.`Score` FROM student stu 
INNER JOIN choose ON choose.`SNO` = stu.`SNO`
INNER JOIN course ON course.`CourseID` = choose.`CourseID`;
# 题31
SELECT *
  FROM student s1
  WHERE EXISTS
  (SELECT * -- 与张三的学院比较
    FROM student s2
    WHERE s2.`college` = s1.`college` 
    AND s2.`name` = '张三'
    AND NOT s1.`name` = '张三'); -- 去掉本人
# 题32
 SELECT Student.SNO, Score -- 获得：学号、成绩
 FROM Student, choose
 WHERE Student.`SNO` = choose.`SNO`  -- 条件：C1成绩低于张三 
   AND choose.CourseID = 'C1' 
   AND choose.Score < (
     SELECT choose.Score -- 获得：张三C1的成绩
     FROM student, choose
     WHERE student.`SNO` = choose.`SNO`	-- 条件：张三的C1
       AND choose.CourseID = 'C1'
       AND Student.`name` = '张三');  
# 题33
SELECT SNO
  FROM Choose
  WHERE CourseID = 'C1'
 UNION
 SELECT SNO
  FROM Choose
  WHERE CourseID = 'C3'
# 题34
SELECT DISTINCT SNO
  FROM Choose
  WHERE CourseID = 'C1'
UNION
SELECT DISTINCT SNO
  FROM Choose
=======
ALTER DATABASE `task5` CHARACTER SET utf8;

# 题1
CREATE TABLE `cqupt student`(
  `studentid` VARCHAR(10),
  `name` VARCHAR(20),
  `sex` VARCHAR(2),
  `age` INT,
  `Fee` DECIMAL(10, 2),
  `address` VARCHAR(50),
  `memo` VARCHAR(300)
);

# 题2
CREATE TABLE `courseAa`(
  `Aa1`	VARCHAR(20),
  `Aa2`	INT,
  `Aa3`	DECIMAL(10)
);

# 题3
CREATE TABLE `ChooseBb`(
  `Bb1` VARCHAR(30),
  `Bb2`	INT,
  `Bb3`	DECIMAL(6)
);
# 题4
ALTER DATABASE `task5` CHARACTER SET utf8; 
ALTER TABLE `choosebb` ADD `Bb4` VARCHAR(20) NOT NULL DEFAULT '系统测试值';
# 题5
ALTER TABLE `choosebb` MODIFY `bb1` DECIMAL(10);
ALTER TABLE `choosebb` MODIFY `bb2` CHAR(6);
ALTER TABLE `choosebb` MODIFY `bb1` INT(11);
ALTER TABLE `choosebb` MODIFY `bb1` VARCHAR(30);
ALTER TABLE `choosebb` ADD `Bb5` VARCHAR(30) NOT NULL PRIMARY KEY;
# 题6
CREATE VIEW `View_Choosebb`(
`View_bb1`, `View_bb2`, `view_bb3`)
AS SELECT `bb1`, `bb4`, `bb5` FROM `choosebb`;
# 题7
DROP VIEW `View_Choosebb`;	
# 题8
CREATE INDEX `Index_bb2` ON `choosebb`(`bb2`);
CREATE INDEX `Index_bb4` ON `choosebb`(`Bb4` DESC);
# 题9
DROP INDEX `index_bb2` ON `choosebb`;

# 题10
CREATE TABLE `test`(
`name` VARCHAR(20),
`age` INT,
`score` NUMERIC(10, 2),	-- 总长10位，小数长2位
`address` VARCHAR(60)
);
# 题11
INSERT INTO `test` VALUES ('赵一', 20, 580.00, '重邮宿舍 12-3-5');
INSERT INTO test VALUES
('钱二', 19, 540.00, '南海苑 5-2-9'),
('孙三', 21, 555.50, '学生新区 21-5-15'),
('李四', 22, 505.00, '重邮宿舍 8-6-22'),
('周五', 20, 495.50, '学生新区 23-4-8'),
('吴六', 19, 435.00, '南海苑 2-5-12');
# 题12
CREATE TABLE test_temp (
`name` VARCHAR(20),
`age` INT,
score NUMERIC(10, 2),
address VARCHAR(60)
);
# 题13
INSERT INTO test_temp VALUES
('郑七', 19, 540.00, '重邮宿舍 11-2-1'),
('张八', 21, 555.50, '南海苑 3-3-3'),
('王九', 22, 505.00, '学生新区 19-7-1');
# 题14
INSERT INTO test SELECT * FROM test_temp;
# 题15
UPDATE test SET score=score+5 WHERE age<=20;
# 题16
UPDATE test SET age=age-1 WHERE address LIKE '南海苑%';
# 题17
DELETE FROM test WHERE age>=21 AND score>=500;
# 题18
DELETE FROM test WHERE score<550 AND address LIKE '重邮宿舍%';

# 题19
CREATE TABLE Student(
SNO VARCHAR(20),
`name` VARCHAR(10),
age INT,
college VARCHAR(30)
);
# 题20
CREATE TABLE Course(
CourseID VARCHAR(15),
CourseName VARCHAR(30),
CourseBeforeID VARCHAR(15)
);
# 题21
CREATE TABLE Choose(
SNO VARCHAR(20),
CourseID VARCHAR(30),
Score DECIMAL(5, 2));
# 题22
INSERT INTO student VALUES
('S00001', '张三', 20, '计算机学院'),
('S00002', '李四', 19, '通信学院'),
('S00003', '王五', 21, '计算机学院');
# 题23
INSERT INTO course VALUES
('C1', '计算机引论', NULL),
('C2', 'C 语言', 'c1'),
('C3', '数据结构', 'c2');
# 题24
INSERT INTO choose VALUES
('S00001', 'C1', 95),
('S00001', 'C2', 80),
('S00001', 'C3', 84),
('S00002', 'C1', 80),
('S00002', 'C2', 85),
('S00003', 'C1', 78),
('S00003', 'C3', 70);

# 题25
SELECT sno, `name` FROM student;
# 题26
SELECT * FROM student WHERE age BETWEEN 20 AND 23;
# 题27
SELECT COUNT(*) FROM student;
# 题28
SELECT MAX(score) FROM choose;
SELECT MIN(score) FROM choose;
SELECT SUM(score) FROM choose;
SELECT AVG(score) FROM choose; 
# 题29
SELECT CourseID, CourseName FROM course WHERE CourseBeforeID IS NULL;
# 题30
SELECT stu.`SNO`, stu.`name`, course.`CourseName`, choose.`Score` FROM student stu 
INNER JOIN choose ON choose.`SNO` = stu.`SNO`
INNER JOIN course ON course.`CourseID` = choose.`CourseID`;
# 题31
SELECT *
  FROM student s1
  WHERE EXISTS
  (SELECT * -- 与张三的学院比较
    FROM student s2
    WHERE s2.`college` = s1.`college` 
    AND s2.`name` = '张三'
    AND NOT s1.`name` = '张三'); -- 去掉本人
# 题32
 SELECT Student.SNO, Score -- 获得：学号、成绩
 FROM Student, choose
 WHERE Student.`SNO` = choose.`SNO`  -- 条件：C1成绩低于张三 
   AND choose.CourseID = 'C1' 
   AND choose.Score < (
     SELECT choose.Score -- 获得：张三C1的成绩
     FROM student, choose
     WHERE student.`SNO` = choose.`SNO`	-- 条件：张三的C1
       AND choose.CourseID = 'C1'
       AND Student.`name` = '张三');  
# 题33
SELECT SNO
  FROM Choose
  WHERE CourseID = 'C1'
 UNION
 SELECT SNO
  FROM Choose
  WHERE CourseID = 'C3'
# 题34
SELECT DISTINCT SNO
  FROM Choose
  WHERE CourseID = 'C1'
UNION
SELECT DISTINCT SNO
  FROM Choose
>>>>>>> 297fd21353979dc2edcd938cfde2febd071ae301
  WHERE COurseID = 'C3'