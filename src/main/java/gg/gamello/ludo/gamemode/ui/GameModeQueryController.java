package gg.gamello.ludo.gamemode.ui;

import gg.gamello.ludo.gamemode.application.GameModeApplicationService;
import gg.gamello.ludo.gamemode.application.dto.GameModeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameModeQueryController {

	@Autowired
	private GameModeApplicationService applicationService;

	@GetMapping("/gamemode")
	public List<GameModeDto> getAll() {
		return applicationService.getAll();
	}

	@GetMapping("/client")
	public String client() {
		return "<!doctype html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"\t<meta charset=\"UTF-8\">\n" +
				"\t<meta name=\"viewport\"\n" +
				"\t\t  content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
				"\t<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
				"\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n" +
				"\t<title>WS test</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"<div class=\"container\">\n" +
				"\t<div class=\"row mb-3\">\n" +
				"\t\t<div class=\"col-12\">\n" +
				"\t\t\t<h3>Authentication</h3>\n" +
				"\t\t\t<label for=\"identities\">Identity presets</label>\n" +
				"\t\t\t<select class=\"form-control mb-3\" id=\"identities\">\n" +
				"\t\t\t\t<option value=\"31d46bce-9445-4920-9531-62cafc3afa1b:eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIzMWQ0NmJjZS05NDQ1LTQ5MjAtOTUzMS02MmNhZmMzYWZhMWIiLCJleHAiOjE1NzM2MzgyODQsImlzcyI6Imh0dHBzOi8vd3d3LmdhbWVsbG8uZ2ciLCJ1c2VybmFtZSI6IlJ1ZHkiLCJlbWFpbCI6InRlc3RAdGVzdC5jdW0iLCJyb2wiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfQ.TS7eLpXaPwpIYG9zwgXs9JHdo6kdShVDJCFk0ZjCyYwYnzSgj6ueBcPj7QxmCFtfeXhe2Xf8RfOua6sa28qf7m6RlQAy2NlirXtR_TLTTX44unMXd0aVUitndh_Uraqblw7I-teJ1n1yxebbMww3S1JtNwZ5JK7FJV3Lr_6l-pf01NTfIeIFD62trCeO5-8H6mL50KaPNAXKEYogq_Dm02XXwBFpP0-V-LNU9tGzHWXTg9l519clr3sJRVQavv4-APx90m2n915jaGB_G-CwGIYRu4OWZM2LhVFzi24BsG4bZDL-YeOBud2-bmLml1TO0kDMY6fopuNVNwzb91-cPg\">Rudy</option>\n" +
				"\t\t\t\t<option value=\"9964e04f-a125-4797-a163-75c4471315dd:eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI5OTY0ZTA0Zi1hMTI1LTQ3OTctYTE2My03NWM0NDcxMzE1ZGQiLCJleHAiOjE1NzM2MzgyMDksImlzcyI6Imh0dHBzOi8vd3d3LmdhbWVsbG8uZ2ciLCJ1c2VybmFtZSI6IlppZ3MiLCJlbWFpbCI6InRlc3RAdGVzdC5jdW0iLCJyb2wiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfQ.DiiNU5pzuS8D8sl5tEyi6NVRfBke7ESOO3q1wO4X8mZOLxWQeMRqNEJML2IRdENqhJJWb5z3GONAcspiTs4yQulIWq_hNiuPgbJAjuffJLXh08OPr5nmltYC2Cm0nMMSPVJEq-jmF10byKtLr36Z2LXoQdofJBGaNF9i8pOcUl-am2ekBswurt_SLKkF42NIkj5HYri9E-KqlhmIs8ZphG61bHEb2b7VYO-hu-QGWMz0vS6Zqtc_pmu5betvRcoN--EjBHD3E4XgqCANQkrjgLdbowNnlGu_S-EMhQ8p6sV9f3PM5aJEhYhwzolJ-pWfabbondYffxm4_1Zdiu1lmQ\">Zigs</option>\n" +
				"\t\t\t\t<option value=\"e67c9d17-faea-482d-86c3-cd690bfb6005:eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJlNjdjOWQxNy1mYWVhLTQ4MmQtODZjMy1jZDY5MGJmYjYwMDUiLCJleHAiOjE1NzM2MzgzNjQsImlzcyI6Imh0dHBzOi8vd3d3LmdhbWVsbG8uZ2ciLCJ1c2VybmFtZSI6IkhlbnJvbWVpc3RlciIsImVtYWlsIjoidGVzdEB0ZXN0LmN1bSIsInJvbCI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl19.i9NMPji-_0Y6_jym4vy6LJewx4bLhceeorFpuBSWvOdBjKYu3BsPEVkRW_t9cCeKa7zeEXm1qqTWVp8pIF11fJmyHJLKzAoPX2c2IxNPjFjUOs5At_rXbWl63nLtLCoxWv-rMIPncVmsmhr7RH35VC9wql5YOimphJKguTEcfFp3_1kTAWCRnn-zaWgRvsYmvEAS_LsLD01yIuspx0aqIBVuUsOPzA_Rj7AyCUyjAqOppStXFORzyK3YUShkmQDN-3BndIMm-oXPEx3Rdiuxgwbqmd1a_fwJzXLi2b9e7O_BpODp3y8I2yu_BuzPSEoFPlILAyrTi7CjI5XECc7SlA\">Henromeister</option>\n" +
				"\t\t\t\t<option value=\"f76f442a-3e9f-4b07-8021-c74c03cbd922:eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJmNzZmNDQyYS0zZTlmLTRiMDctODAyMS1jNzRjMDNjYmQ5MjIiLCJleHAiOjE1NzM2Mzg0MTcsImlzcyI6Imh0dHBzOi8vd3d3LmdhbWVsbG8uZ2ciLCJ1c2VybmFtZSI6IkpJYXlwYSIsImVtYWlsIjoidGVzdEB0ZXN0LmN1bSIsInJvbCI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl19.JR6rhWEsbiC_mTdjeb8CBxA5lDpMoZZ1iRLKEEzLCJqw80aYcry_sJJy0DTnh22BaCXLCy9xc0qGXbuL3LmJHJiqiL5dziOb1wTmFWeDvobJUbZ-7n2cmtY1SVdVrF4T2aSwYHykbWMK8q0jpwuCA9saYI1wuwrwbi0u_EqEuZTeJJu2JrIQRKCL2Onr3rywvA1WV-oO6wXTUvnXT8Wq-04auE6t5ZrPl8QpRKWopJmRUwyQf-60Z0gk74iqu85mn0NIy2r-FrYVojBTjQVyJfknOEJlRmMoJPx7jvQAdQXGMbtyELCcDevVmhK-DN9S29BMzqhFIAqixi37VQkMrA\">JIaypa</option>\n" +
				"\t\t\t</select>\n" +
				"\n" +
				"\t\t\t<label for=\"token\">Bearer token</label>\n" +
				"\t\t\t<input id=\"token\" type=\"text\" class=\"form-control mb-1\">\n" +
				"\n" +
				"\t\t\t<button id=\"connect\" class=\"btn btn-primary\">Connect</button>\n" +
				"\t\t</div>\n" +
				"\t</div>\n" +
				"\t<div class=\"row\">\n" +
				"\t\t<div class=\"col-12\">\n" +
				"\t\t\t<h3>Game</h3>\n" +
				"\t\t\t<label for=\"game\">Game ID</label>\n" +
				"\t\t\t<input id=\"game\" type=\"text\" class=\"form-control mb-3\">\n" +
				"\n" +
				"\t\t\t<label for=\"subject\">User ID</label>\n" +
				"\t\t\t<input id=\"subject\" type=\"text\" placeholder=\"Subject\" class=\"form-control mb-3\">\n" +
				"\n" +
				"\t\t\t<label for=\"action\">Action</label>\n" +
				"\t\t\t<select id=\"action\" class=\"form-control mb-3\">\n" +
				"\t\t\t\t<option value=\"DICE_ROLL\" selected>Roll dice</option>\n" +
				"\t\t\t\t<option value=\"PAWN_SELECT\">Move pawn</option>\n" +
				"\t\t\t</select>\n" +
				"\n" +
				"\t\t\t<label for=\"pawn\">Pawn ID</label>\n" +
				"\t\t\t<select id=\"pawn\" class=\"form-control mb-3\"></select>\n" +
				"\n" +
				"\t\t\t<button id=\"send\" class=\"btn btn-primary\">Send</button>\n" +
				"\t\t\t<button id=\"subscribe\" class=\"btn btn-primary\">Subscribe</button>\n" +
				"\t\t</div>\n" +
				"\t</div>\n" +
				"\t<div class=\"row\">\n" +
				"\t\t<div class=\"col-3\">\n" +
				"\t\t\t<span id=\"dice-result\"></span>\n" +
				"\t\t</div>\n" +
				"\t</div>\n" +
				"</div>\n" +
				"\n" +
				"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js\"></script>\n" +
				"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js\"></script>\n" +
				"<script>\n" +
				"\tdocument.querySelector('#identities').addEventListener('change', () => {\n" +
				"        let value = document.querySelector('#identities').value;\n" +
				"        let arr = value.split(':');\n" +
				"        document.querySelector('#token').value = arr[1];\n" +
				"        document.querySelector('#subject').value = arr[0];\n" +
				"\t});\n" +
				"\n" +
				"    document.querySelector('#connect').addEventListener('click', () => {\n" +
				"        let headers = {\n" +
				"            'Authorization': 'Bearer ' + document.querySelector('#token').value\n" +
				"        };\n" +
				"        let socket = new SockJS('http://localhost:8080/websocket');\n" +
				"        let stompClient = Stomp.over(socket);\n" +
				"        stompClient.reconnect_delay = 3000;\n" +
				"        stompClient.connect(headers, function (frame) {\n" +
				"            document.querySelector('#send').addEventListener('click', () => {\n" +
				"                let message = {\n" +
				"                    subject: document.querySelector('#subject').value,\n" +
				"                    action: document.querySelector('#action').value,\n" +
				"                    data: {\n" +
				"                        'pawnId': document.querySelector('#pawn').value\n" +
				"                    }\n" +
				"                };\n" +
				"                stompClient.send('/app/ludo/game/' + document.querySelector('#game').value, {}, JSON.stringify(message))\n" +
				"            });\n" +
				"            document.querySelector('#subscribe').addEventListener('click', () => {\n" +
				"                stompClient.subscribe('/topic/ludo/game/' + document.querySelector('#game').value, (m) => {\n" +
				"                    let message = JSON.parse(m.body);\n" +
				"                    if (message.subject === document.querySelector('#subject').value) {\n" +
				"                        if (message.action === \"PAWN_MOVE\") {\n" +
				"                            let pawns = document.querySelector('#pawn');\n" +
				"                            for (let i = 0; i < pawns.options.length; i++) {\n" +
				"                                if (pawns.options[i].value === message.data.pawnId)\n" +
				"                                    pawns.options[i].text = pawns.options[i].value + \" (\" + message.data.position.location + \":\" + message.data.position.square + \")\";\n" +
				"\t\t\t\t\t\t\t}\n" +
				"                        }\n" +
				"                    }\n" +
				"\n" +
				"                    if (message.action === \"ROUND_NEW\") {\n" +
				"                        if (message.subject === document.querySelector('#subject').value) {\n" +
				"                            document.querySelector('#action').style.backgroundColor = \"#bfffbf\";\n" +
				"                        } else {\n" +
				"                            document.querySelector('#action').style.backgroundColor = \"#ffffff\";\n" +
				"\t\t\t\t\t\t}\n" +
				"                    }\n" +
				"\n" +
				"                    if (message.action === \"DICE_RESULT\") {\n" +
				"                        if (message.subject === document.querySelector('#subject').value) {\n" +
				"                            document.querySelector('#dice-result').innerText = \"Dice result is \" + message.data.result;\n" +
				"                        } else {\n" +
				"                            document.querySelector('#dice-result').innerText = \"\";\n" +
				"                        }\n" +
				"\t\t\t\t\t}\n" +
				"\t\t\t\t});\n" +
				"                stompClient.subscribe('/user/queue/ludo', (m) => {\n" +
				"                    let message = JSON.parse(m.body);\n" +
				"                    if (message.action === \"GAME_STATE\") {\n" +
				"                        message.data.game.board.players.forEach(player => {\n" +
				"                            if (player.id === document.querySelector('#subject').value) {\n" +
				"                                let pawns = document.querySelector('#pawn');\n" +
				"                                player.pawns.forEach(pawn => {\n" +
				"                                    let option = document.createElement(\"option\");\n" +
				"                                    option.text = pawn.id + \" (\" + pawn.position.location + \":\" + pawn.position.square + \")\";\n" +
				"                                    option.value = pawn.id;\n" +
				"                                    pawns.add(option);\n" +
				"                                })\n" +
				"                            }\n" +
				"                        });\n" +
				"\n" +
				"                        if (message.data.game.board.currentPlayer !== undefined) {\n" +
				"                            if (message.data.game.board.currentPlayer === document.querySelector('#subject').value) {\n" +
				"                                document.querySelector('#action').style.backgroundColor = \"#bfffbf\";\n" +
				"\t\t\t\t\t\t\t}\n" +
				"\t\t\t\t\t\t}\n" +
				"                    }\n" +
				"\t\t\t\t});\n" +
				"            });\n" +
				"        });\n" +
				"\n" +
				"    });\n" +
				"</script>\n" +
				"</body>\n" +
				"</html>";
	}
}
