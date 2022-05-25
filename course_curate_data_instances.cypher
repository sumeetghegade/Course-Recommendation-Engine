// a cypher file to populate any database with our mock nodes and relationships

// ---------- EMPTY THE CURRENT DATABASE ----------
// comment the following 2 lines to keep other existing nodes in the database
MATCH (n)
DETACH DELETE n;

// ---------- NODES ----------
// all passwords are "password" before encryption

// User nodes with role "Student"
CREATE (alice:User { emailID: 'alice@sjsu.edu', name: 'Alice Lopez', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (juan:User { emailID: 'juan@sjsu.edu', name: 'Juan Jones', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (kenneth:User { emailID: 'kenneth@sjsu.edu', name: 'Kenneth Bryant', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (jerry:User { emailID: 'jerry@sjsu.edu', name: 'Jerry Davis', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (william:User { emailID: 'william@sjsu.edu', name: 'William Rodriguez', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (frances:User { emailID: 'frances@sjsu.edu', name: 'Frances Washington', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (ann:User { emailID: 'ann@sjsu.edu', name: 'Ann Parker', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (victor:User { emailID: 'victor@sjsu.edu', name: 'Victor Wood', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (james:User { emailID: 'james@sjsu.edu', name: 'James Williams', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (debra:User { emailID: 'debra@sjsu.edu', name: 'Debra Taylor', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (joyce:User { emailID: 'joyce@sjsu.edu', name: 'Joyce Ramirez', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (jose:User { emailID: 'jose@sjsu.edu', name: 'Jose Martin', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (emily:User { emailID: 'emily@sjsu.edu', name: 'Emily Harris', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (john:User { emailID: 'john@sjsu.edu', name: 'John Allen', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (bruce:User { emailID: 'bruce@sjsu.edu', name: 'Bruce Jenkins', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (paul:User { emailID: 'paul@sjsu.edu', name: 'Paul Brooks', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (tk:User { emailID: 'tk@sjsu.edu', name: 'T.K. Bui', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (sumeet:User { emailID: 'sumeet.ghegade@sjsu.edu', name: 'Sumeet Ghegade', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })
CREATE (shota:User { emailID: 'shota.t@sjsu.edu', name: 'Shota Tanaka', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Student' })

// User nodes with role "Faculty"
CREATE (navrati:User { emailID: 'navrati.saxena@sjsu.edu', name: 'Navrati Saxena', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (masshour:User { emailID: 'masshour.solh@sjsu.edu', name: 'Masshour Solh', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (chris:User { emailID: 'chris.pollett@sjsu.edu', name: 'Chris Pollett', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (ben:User { emailID: 'ben.reed@sjsu.edu', name: 'Ben Reed', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (williamA:User { emailID: 'william.andreopoulos@sjsu.edu', name: 'William B. Andreopoulos', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (nada:User { emailID: 'nada.attar@sjsu.edu', name: 'Nada Attar', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (thomas:User { emailID: 'thomas.austin@sjsu.edu', name: 'Thomas Austin', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (alexandra:User { emailID: 'alexandra.chakarov@sjsu.edu', name: 'Alexandra Chakarov', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (robert:User { emailID: 'robert.chun@sjsu.edu', name: 'Robert Chun', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (fabio:User { emailID: 'fabio.ditroia@sjsu.edu', name: 'Fabio Di Troia', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (genya:User { emailID: 'genya.ishigaki@sjsu.edu', name: 'Genya Ishigaki', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (katerina:User { emailID: 'katerina.potika@sjsu.edu', name: 'Katerina Potika', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (mark:User { emailID: 'mark.stamp@sjsu.edu', name: 'Mark Stamp', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (kong:User { emailID: 'kong.li@sjsu.edu', name: 'Kong Li', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })
CREATE (mike:User { emailID: 'mike.wu@sjsu.edu', name: 'Mike Wu', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Faculty' })

// User nodes with role "Admin"
CREATE (admin:User { emailID: 'chris.parker@sjsu.edu', name: 'Chris Parker', password: '$2a$12$8Wd8iKI3D3WpTa6kg9GxGuU0S3hz8N2RVNYIugXJIHkFOeUbJ/R/G', role: 'Admin' })

// Course nodes
CREATE (ccs:Course {name: 'Computer Communication Systems'})
CREATE (ml:Course {name: 'Topics in Machine Learning'})
CREATE (algos:Course {name: 'Design and Analysis of Algorithms'})
CREATE (distComp:Course {name: 'Distributed Computing'})
CREATE (humanCompInter:Course {name: 'Human Computer Interaction'})
CREATE (bioinf:Course {name: 'Bioinformatics'})
CREATE (sbml:Course {name: 'Topics in Sequence-based Machine Learning for Bioinformatics'})
CREATE (ds:Course {name: 'Introduction to Data Structures'})
CREATE (pp:Course {name: 'Advanced Parallel Processing'})
CREATE (introml:Course {name: 'Introduction to Machine Learning'})
CREATE (rl:Course {name: 'Reinforcement Learning'})
CREATE (infosec:Course {name: 'Topics in Information Security'})
CREATE (os:Course {name: 'Operating Systems'})
CREATE (dbs:Course {name: 'Topics in Database Systems'})
CREATE (cc:Course {name: 'Cloud Computing'})
CREATE (ui:Course {name: 'User Interface Design'})
CREATE (wn:Course {name: 'Topics in Wireless Networks'})
CREATE (nosql:Course {name: 'NoSQL'})
CREATE (dsa:Course {name: 'Data Structures and Algorithms'})
CREATE (ai:Course {name: 'Topics in Artificial Intelligence'})

// Domain nodes
CREATE (aiDom:Domain {name: 'Artificial Intelligence'})
CREATE (mlDom:Domain {name: 'Machine Learning'})
CREATE (progDom:Domain {name: 'Programming'})
CREATE (dsDom:Domain {name: 'Data Structures'})
CREATE (algoDom:Domain {name: 'Algorithms'})
CREATE (infraDom:Domain {name: 'Infrastructure'})
CREATE (osDom:Domain {name: 'Operating Systems'})
CREATE (nwDom:Domain {name: 'Networking'})
CREATE (dbDom:Domain {name: 'Databases'})
CREATE (sdDom:Domain {name: 'System Design'})
CREATE (webDom:Domain {name: 'Web Applications'})
CREATE (ccDom:Domain {name: 'Cloud Computing'})
CREATE (distsysDom:Domain {name: 'Distributed Systems'})
CREATE (cvDom:Domain {name: 'Computer Vision'})
CREATE (secDom:Domain {name: 'Security'})

// ---------- RELATIONSHIPS ----------

// (:User {role: 'Student'})-[:INTERESTED_IN]->(:Domain)

CREATE (alice)-[:INTERESTED_IN]->(dbDom)
CREATE (alice)-[:INTERESTED_IN]->(dsDom)
CREATE (alice)-[:INTERESTED_IN]->(algoDom)

CREATE (juan)-[:INTERESTED_IN]->(aiDom)
CREATE (juan)-[:INTERESTED_IN]->(mlDom)
CREATE (juan)-[:INTERESTED_IN]->(progDom)

CREATE (kenneth)-[:INTERESTED_IN]->(infraDom)
CREATE (kenneth)-[:INTERESTED_IN]->(osDom)
CREATE (kenneth)-[:INTERESTED_IN]->(nwDom)

CREATE (jerry)-[:INTERESTED_IN]->(dbDom)
CREATE (jerry)-[:INTERESTED_IN]->(aiDom)
CREATE (jerry)-[:INTERESTED_IN]->(infraDom)

CREATE (william)-[:INTERESTED_IN]->(dsDom)
CREATE (william)-[:INTERESTED_IN]->(progDom)
CREATE (william)-[:INTERESTED_IN]->(sdDom)

CREATE (frances)-[:INTERESTED_IN]->(webDom)
CREATE (frances)-[:INTERESTED_IN]->(ccDom)
CREATE (frances)-[:INTERESTED_IN]->(distsysDom)

CREATE (ann)-[:INTERESTED_IN]->(cvDom)
CREATE (ann)-[:INTERESTED_IN]->(secDom)
CREATE (ann)-[:INTERESTED_IN]->(dsDom)

CREATE (victor)-[:INTERESTED_IN]->(dsDom)
CREATE (victor)-[:INTERESTED_IN]->(algoDom)
CREATE (victor)-[:INTERESTED_IN]->(nwDom)

CREATE (james)-[:INTERESTED_IN]->(aiDom)
CREATE (james)-[:INTERESTED_IN]->(mlDom)
CREATE (james)-[:INTERESTED_IN]->(progDom)

CREATE (debra)-[:INTERESTED_IN]->(infraDom)
CREATE (debra)-[:INTERESTED_IN]->(osDom)
CREATE (debra)-[:INTERESTED_IN]->(nwDom)

CREATE (joyce)-[:INTERESTED_IN]->(webDom)
CREATE (joyce)-[:INTERESTED_IN]->(progDom)
CREATE (joyce)-[:INTERESTED_IN]->(secDom)

CREATE (jose)-[:INTERESTED_IN]->(ccDom)
CREATE (jose)-[:INTERESTED_IN]->(distsysDom)
CREATE (jose)-[:INTERESTED_IN]->(cvDom)

CREATE (emily)-[:INTERESTED_IN]->(secDom)
CREATE (emily)-[:INTERESTED_IN]->(osDom)
CREATE (emily)-[:INTERESTED_IN]->(nwDom)

CREATE (john)-[:INTERESTED_IN]->(progDom)
CREATE (john)-[:INTERESTED_IN]->(dsDom)
CREATE (john)-[:INTERESTED_IN]->(algoDom)

CREATE (bruce)-[:INTERESTED_IN]->(dbDom)
CREATE (bruce)-[:INTERESTED_IN]->(progDom)
CREATE (bruce)-[:INTERESTED_IN]->(webDom)

CREATE (paul)-[:INTERESTED_IN]->(cvDom)
CREATE (paul)-[:INTERESTED_IN]->(distsysDom)
CREATE (paul)-[:INTERESTED_IN]->(dbDom)

CREATE (tk)-[:INTERESTED_IN]->(webDom)
CREATE (tk)-[:INTERESTED_IN]->(progDom)
CREATE (tk)-[:INTERESTED_IN]->(dbDom)

CREATE (sumeet)-[:INTERESTED_IN]->(dbDom)
CREATE (sumeet)-[:INTERESTED_IN]->(aiDom)
CREATE (sumeet)-[:INTERESTED_IN]->(mlDom)

CREATE (shota)-[:INTERESTED_IN]->(dbDom)
CREATE (shota)-[:INTERESTED_IN]->(ccDom)
CREATE (shota)-[:INTERESTED_IN]->(nwDom)

// (:User {role: 'Student'})-[:TAKEN]->(:Course)

CREATE (kenneth)-[:TAKEN]->(ccs)
CREATE (debra)-[:TAKEN]->(ccs)

CREATE (juan)-[:TAKEN]->(ml)
CREATE (james)-[:TAKEN]->(ml)

CREATE (alice)-[:TAKEN]->(algos)
CREATE (victor)-[:TAKEN]->(algos)

CREATE (frances)-[:TAKEN]->(distComp)
CREATE (jose)-[:TAKEN]->(distComp)

CREATE (alice)-[:TAKEN]->(bioinf)
CREATE (juan)-[:TAKEN]->(bioinf)

CREATE (juan)-[:TAKEN]->(sbml)
CREATE (james)-[:TAKEN]->(sbml)

CREATE (alice)-[:TAKEN]->(ds)
CREATE (william)-[:TAKEN]->(ds)

CREATE (juan)-[:TAKEN]->(introml)
CREATE (william)-[:TAKEN]->(introml)

CREATE (ann)-[:TAKEN]->(infosec)
CREATE (joyce)-[:TAKEN]->(infosec)

CREATE (kenneth)-[:TAKEN]->(os)
CREATE (debra)-[:TAKEN]->(os)

CREATE (frances)-[:TAKEN]->(ui)
CREATE (joyce)-[:TAKEN]->(ui)

CREATE (victor)-[:TAKEN]->(wn)
CREATE (debra)-[:TAKEN]->(wn)

CREATE (alice)-[:TAKEN]->(nosql)
CREATE (jerry)-[:TAKEN]->(nosql)

CREATE (alice)-[:TAKEN]->(dsa)
CREATE (william)-[:TAKEN]->(dsa)

CREATE (juan)-[:TAKEN]->(ai)
CREATE (jerry)-[:TAKEN]->(ai)

// (:User {role: 'Faculty'})-[:TEACHES]->(:Course)

CREATE (navrati)-[:TEACHES]->(ccs)
CREATE (masshour)-[:TEACHES]->(ml)
CREATE (chris)-[:TEACHES]->(algos)
CREATE (ben)-[:TEACHES]->(distComp)
CREATE (williamA)-[:TEACHES]->(humanCompInter)
CREATE (nada)-[:TEACHES]->(bioinf)
CREATE (thomas)-[:TEACHES]->(sbml)
CREATE (alexandra)-[:TEACHES]->(ds)
CREATE (robert)-[:TEACHES]->(pp)
CREATE (fabio)-[:TEACHES]->(introml)
CREATE (genya)-[:TEACHES]->(rl)
CREATE (katerina)-[:TEACHES]->(infosec)
CREATE (mark)-[:TEACHES]->(os)
CREATE (kong)-[:TEACHES]->(dbs)
CREATE (ben)-[:TEACHES]->(cc)
CREATE (fabio)-[:TEACHES]->(ui)
CREATE (robert)-[:TEACHES]->(wn)
CREATE (mike)-[:TEACHES]->(nosql)
CREATE (alexandra)-[:TEACHES]->(dsa)
CREATE (masshour)-[:TEACHES]->(ai)
