--
-- Datenbank: `sql2263400`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `files`
--

CREATE TABLE `files` ( `FILE_NAME` varchar(128) NOT NULL, `File_DELETED_AT` datetime DEFAULT NULL, `File_DELETER` varchar(50) DEFAULT NULL, `FILE_PATH` varchar(191) DEFAULT NULL, `FILE_UPLOADED` tinyint(1) DEFAULT '0', `File_UPLOADED_AT` datetime DEFAULT NULL, `File_UPLOADER` varchar(50) DEFAULT NULL, `FILE_DELETED` tinyint(1) DEFAULT '0', `USER_EMAIL` varchar(50) DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `files`
--

INSERT INTO `files` (`FILE_NAME`, `File_DELETED_AT`, `File_DELETER`, `FILE_PATH`, `FILE_UPLOADED`, `File_UPLOADED_AT`, `File_UPLOADER`, `FILE_DELETED`, `USER_EMAIL`) VALUES ('CourseScheduleAngewandteinformatikBachelorofscienceSommersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/aed58291-9e3d-49fa-965f-408a18fa4f89', 1, '2018-10-31 15:10:15', 'Rudi', 0, NULL), ('CourseScheduleAngewandteinformatikBachelorofscienceWintersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/6e0eaf84-a222-431c-9ad6-669baffd9017', 1, '2018-10-31 15:10:14', 'Rudi', 0, NULL), ('CourseScheduleAngewandteinformatikMasterofscienceSommersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/a6fc8840-50e8-4fd5-b687-e2842e97b71d', 1, '2018-10-31 15:10:17', 'Rudi', 0, NULL), ('CourseScheduleAngewandteinformatikMasterofscienceWintersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/14f5a6a6-56f7-4fd8-89ee-ac8e1f2cb692', 1, '2018-10-31 15:10:16', 'Rudi', 0, NULL), ('CourseScheduleWirtschaftsinformatikBachelorofscienceSommersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/acaee0d6-ae98-4eed-b8e5-1be62c67a703', 1, '2018-10-31 15:10:18', 'Rudi', 0, NULL), ('CourseScheduleWirtschaftsinformatikBachelorofscienceWintersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/152a6db7-dc6b-47be-9792-ba2fa126919c', 1, '2018-10-31 15:10:18', 'Rudi', 0, NULL), ('CourseScheduleWirtschaftsinformatikMasterofscienceSommersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/e48e1ddd-a713-4e2c-bdd1-9e7177f8efca', 1, '2018-10-31 15:10:20', 'Rudi', 0, NULL), ('CourseScheduleWirtschaftsinformatikMasterofscienceWintersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/ec049ecb-2fce-4cc6-8a55-f9d8397144e9', 1, '2018-10-31 15:10:19', 'Rudi', 0, NULL), ('LectureScheduleAngewandteinformatikBachelorofscienceSommersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/ef100311-76ec-4b90-970f-388cc64920be', 1, '2018-10-31 14:37:36', 'Rudi', 0, NULL), ('LectureScheduleAngewandteinformatikBachelorofscienceWintersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/ab13fddf-9ac0-48bc-8684-05fa014bd8e2', 1, '2018-10-31 14:37:35', 'Rudi', 0, NULL), ('LectureScheduleAngewandteinformatikMasterofscienceSommersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/e0b8a8a2-b34d-43a6-bdad-bd269451e547', 1, '2018-10-31 14:37:38', 'Rudi', 0, NULL), ('LectureScheduleAngewandteinformatikMasterofscienceWintersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/042a06c3-0e7d-484a-accd-bb8c5bf5ead7', 1, '2018-10-31 14:37:37', 'Rudi', 0, NULL), ('LectureScheduleWirtschaftsinformatikBachelorofscienceSommersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/c79436b0-2aa6-42b5-803b-a7087544733c', 1, '2018-10-31 14:38:16', 'Rudi', 0, NULL), ('LectureScheduleWirtschaftsinformatikBachelorofscienceWintersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/21472867-dfa6-4684-b712-07e4b3768d3e', 1, '2018-10-31 14:37:39', 'Rudi', 0, NULL), ('LectureScheduleWirtschaftsinformatikMasterofscienceSommersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/9218fee6-40e0-41c7-86ab-d2485e866245', 1, '2018-10-31 14:38:18', 'Rudi', 0, NULL), ('LectureScheduleWirtschaftsinformatikMasterofscienceWintersemester', NULL, NULL, 'src/test/resources/Files/LectureAssets/d4e8e4e3-94b3-4c1b-8388-fe851a58d325', 1, '2018-10-31 14:38:17', 'Rudi', 0, NULL);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `files`
--
ALTER TABLE `files` ADD PRIMARY KEY (`FILE_NAME`); COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
