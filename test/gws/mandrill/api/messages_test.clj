(ns gws.mandrill.api.messages-test
  (:refer-clojure :exclude [send])
  (:require [clojure.test :refer :all]
            [gws.mandrill.client :as c]
            [gws.mandrill.api.messages :as m]))

(def client
  (c/create (System/getenv "MANDRILL_TEST_API_KEY")))

(def msg1
  {:message {:to [{:email "foo@bar.com"
                   :name "Foo Bar"}]
             :from_email "foo@bar.com"}})

(deftest ^:integration send
  (let [response (first (m/send client msg1))]
    (is (= {:email "foo@bar.com" :status "sent"}
           (select-keys response [:email :status])))))
