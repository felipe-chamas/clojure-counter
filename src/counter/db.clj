(ns counter.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:mem://counter")

(def schema
  [{:db/ident :counter/id
    :db/valueType :db.type/keyword
    :db/cardinality :db.cardinality/one
    :db/unique :db.unique/identity}
   {:db/ident :counter/value
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}])

(defn init-db! []
  (d/create-database db-uri)
  (let [conn (d/connect db-uri)]
    @(d/transact conn schema)
    @(d/transact conn [{:counter/id :a :counter/value 0}
                       {:counter/id :b :counter/value 0}])
    conn))

(defn get-counter [db id]
  (or (:counter/value (d/pull db [:counter/value] [:counter/id id])) 0))

(defn get-counters [db]
  {:a (get-counter db :a) :b (get-counter db :b)})

(defn increase-counter! [conn id]
  (let [current (get-counter (d/db conn) id)]
    @(d/transact conn [[:db/add [:counter/id id] :counter/value (inc current)]])
    (get-counters (d/db conn))))

(defn reset-counter! [conn id]
  @(d/transact conn [[:db/add [:counter/id id] :counter/value 0]])
  (get-counters (d/db conn)))
