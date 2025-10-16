(ns counter.core)

(defonce state (atom {:counters {:a 0 :b 0} :selected :a}))

(declare render!)

(defn fetch-counters! []
  (-> (js/fetch "/api/counters")
      (.then #(.json %))
      (.then #(swap! state assoc :counters (js->clj % :keywordize-keys true)))
      (.then render!)))

(defn increase! [id]
  (-> (js/fetch (str "/api/increase/" (name id)) #js {:method "POST"})
      (.then #(.json %))
      (.then #(swap! state assoc :counters (js->clj % :keywordize-keys true)))
      (.then render!)))

(defn reset-counter! [id]
  (-> (js/fetch (str "/api/reset/" (name id)) #js {:method "POST"})
      (.then #(.json %))
      (.then #(swap! state assoc :counters (js->clj % :keywordize-keys true)))
      (.then render!)))

(defn select-counter! [id]
  (swap! state assoc :selected id)
  (render!))

(defn counter-card [id value selected?]
  (str "<div class='counter-card" (if selected? " selected" "") "'>"
       "  <h3>Counter " (clojure.string/upper-case (name id)) "</h3>"
       "  <div class='counter-value'>" value "</div>"
       "</div>"))

(defn slider []
  (let [selected (:selected @state)]
    (str "<div class='slider-container'>"
         "  <div class='slider-track' id='slider-track'>"
         "    <div class='slider-thumb" (if (= selected :b) " right" "") "' id='slider-thumb'></div>"
         "    <div class='slider-label left" (if (= selected :a) " active" "") "'>A</div>"
         "    <div class='slider-label right" (if (= selected :b) " active" "") "'>B</div>"
         "  </div>"
         "</div>")))

(defn render! []
  (let [{:keys [counters selected]} @state
        app-div (.getElementById js/document "app")]
    (set! (.-innerHTML app-div)
          (str "<div class='container'>"
               "  <h1>Counter App</h1>"
               "  <div class='counters'>"
               (counter-card :a (:a counters) (= selected :a))
               (counter-card :b (:b counters) (= selected :b))
               "  </div>"
               "  <div class='section'>"
               "    <h3>Select Counter</h3>"
               (slider)
               "  </div>"
               "  <div class='section'>"
               "    <h3>Actions</h3>"
               "    <div class='actions'>"
               "      <button id='increase' class='btn btn-primary'>Increase Selected</button>"
               "      <button id='reset' class='btn btn-secondary'>Reset Selected</button>"
               "    </div>"
               "  </div>"
               "</div>"))
    (.addEventListener (.getElementById js/document "slider-track") "click"
                       (fn [] (select-counter! (if (= (:selected @state) :a) :b :a))))
    (.addEventListener (.getElementById js/document "increase") "click" #(increase! (:selected @state)))
    (.addEventListener (.getElementById js/document "reset") "click" #(reset-counter! (:selected @state)))))

(defn init []
  (fetch-counters!))
