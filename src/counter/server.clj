(ns counter.server
  (:require [org.httpkit.server :as http]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :refer [response]]
            [counter.db :as db]
            [datomic.api :as d]))

(def conn (atom nil))

(defroutes app-routes
  (GET "/" [] (slurp "resources/public/index.html"))
  (GET "/api/counters" [] (response (db/get-counters (d/db @conn))))
  (POST "/api/increase/:id" [id] (response (db/increase-counter! @conn (keyword id))))
  (POST "/api/reset/:id" [id] (response (db/reset-counter! @conn (keyword id))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      wrap-json-response
      (wrap-json-body {:keywords? true})
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))

(defn start-server! [port]
  (reset! conn (db/init-db!))
  (http/run-server app {:port port})
  (println (str "Server running on http://localhost:" port)))

(defn -main [] (start-server! 3000))
