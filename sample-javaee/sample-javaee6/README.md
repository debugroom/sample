#jboss-6.1.0.FINALにおけるデータソース追加手順

1. 使用するJDBCのドライバを"common/lib"に配置。
1. "docs/examples/jca/xxxxxx-ds.xml"のひな形を編集し、"server/default/deploy"に配置。
1. WebアプリケーションのWARファイルを"server/default/deploy"に配置。
