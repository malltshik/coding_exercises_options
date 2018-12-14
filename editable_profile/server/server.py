#!/usr/bin/env python3

from http.server import BaseHTTPRequestHandler, HTTPServer
import json
import os

HOST_NAME = 'localhost'
PORT_NUMBER = 9000
WORKDIR = os.path.dirname(os.path.abspath(__file__))


class MyHandler(BaseHTTPRequestHandler):
    def do_GET(self):

        paths = {
            '/locations': WORKDIR + '/en/locations/cities.json',
            '/attributes': WORKDIR + '/en/single_choice_attributes.json',
        }

        if self.path not in paths:
            self.send_response(404)
            self.wfile.write(b'')
        else:
            self.send_response(200)
            self.send_header('Content-type', 'application/json')
            self.end_headers()
            with open(paths[self.path]) as f:
                data = json.load(f)
            self.wfile.write(bytes(json.dumps(data), 'UTF-8'))


if __name__ == '__main__':
    server_class = HTTPServer
    httpd = server_class((HOST_NAME, PORT_NUMBER), MyHandler)
    try:
        print("Attribute server started")
        httpd.serve_forever()
    except KeyboardInterrupt:
        pass
    httpd.server_close()
