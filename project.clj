(defproject com.enterpriseweb/open-stack-wrapper-java-api "0.1.0"
  :description "this clojure project only contains the code for generating the API java class that will be used on java environments"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.enterpriseweb/open-stack-wrapper "0.1.11"]]
  :scm {:name "git"
        :url "https://github.com/tangrammer/open-stack-wrapper-java-api"}

  :aot [com.enterpriseweb.openstack.OpenStackAPI]
  )
