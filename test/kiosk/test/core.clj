(ns kiosk.core
  (:use clojure.test
        ring.mock.request
        kiosk.core))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))
